package hello.jdbc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import hello.jdbc.repository.MemberRepositoryV5;
import lombok.extern.slf4j.Slf4j;
/**
 *  예외 누수 문제 해결
 *  SQLException 제거
 *  
 *  MemberRepository 인터페이스 의존
 * */
@Slf4j
@SpringBootTest
public class MemberServiceV4Test {

	public static final String MEMBER_A  = "memberA";
	public static final String MEMBER_B  = "memberB";
	public static final String MEMBER_EX = "ex";
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberServiceV4 memberService;
	
	@AfterEach
	void after() throws SQLException {
		memberRepository.delete(MEMBER_A);
		memberRepository.delete(MEMBER_B);
		memberRepository.delete(MEMBER_EX);
	}
	
	@TestConfiguration
	static class TestConfig{
		
		/* 스프링 부트는 dataSource를 스프링 빈에 자동등록 
		 * 자동 등록되는 스프링 빈 이름은 dataSource
		 * 결국 TestConfig dataSource를 주입하는 것으 스프링 부트가 진행하고 
		 * 주입되는 값은 application.properties를 참고해서 들고온다.
		 */ 
		private final DataSource dataSource;
		
		public TestConfig(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		@Bean
		MemberRepository memberRepository() {
			return new MemberRepositoryV5(this.dataSource);
		}
		
		@Bean
		MemberServiceV4 memberServiceV4() {
			return new MemberServiceV4(memberRepository());
		}
	}
	
	@Test
	void AopCheck() {
		log.info("memberService class={}", memberService.getClass());
		log.info("memberRepository class={}", memberRepository.getClass());
		Assertions.assertThat(AopUtils.isAopProxy(memberService)).isTrue();
		Assertions.assertThat(AopUtils.isAopProxy(memberRepository)).isTrue();
	}
	
	@Test
	@DisplayName("정상 이체")
	void accountTransfer() throws SQLException {
		// given
		Member memberA = new Member(MEMBER_A, 10000);
		Member memberB = new Member(MEMBER_B, 10000);
		memberRepository.save(memberA);
		memberRepository.save(memberB);
		
		// when
		memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);
		
		// then
		Member findMemberA = memberRepository.findById(memberA.getMemberId());
		Member findMemberB = memberRepository.findById(memberB.getMemberId());
		assertThat(findMemberA.getMoney()).isEqualTo(8000);
		assertThat(findMemberB.getMoney()).isEqualTo(12000);
	}
	
	@Test
	@DisplayName("이체중 예외 발생")
	void accountTransferEx() throws SQLException {
		// given : 데이터를 저장해 테스트를 준비
		Member memberA  = new Member(MEMBER_A, 10000);
		Member memberEx = new Member(MEMBER_EX, 10000);
		memberRepository.save(memberA);
		memberRepository.save(memberEx);
		
		// when : 계좌 이체 로직을 실행
		assertThatThrownBy(() -> memberService.accountTransfer(memberA.getMemberId(),
															   memberEx.getMemberId(), 
															   2000))
							.isInstanceOf(IllegalStateException.class);
		
		// then : 계좌 이체 실패, memberA의 돈만 2000원 줄어듦
		Member findMemberA  = memberRepository.findById(memberA.getMemberId());
		Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
		
		// PlatformTransactionManager로 Connection을 관리한 뒤에는 처리 과정에서 에러가 발생하면
		// 트랜잭션을 rollback 처리
		assertThat(findMemberA.getMoney()).isEqualTo(10000);
		assertThat(findMemberEx.getMoney()).isEqualTo(10000);
	}
}
