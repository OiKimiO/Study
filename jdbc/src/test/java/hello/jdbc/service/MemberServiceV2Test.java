package hello.jdbc.service;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
/**
 *  기본 동작, 트랜잭션이 없어서 문제 발생
 * */
public class MemberServiceV2Test {

	public static final String MEMBER_A  = "memberA";
	public static final String MEMBER_B  = "memberB";
	public static final String MEMBER_EX = "ex";
	
	private MemberRepositoryV2 memberRespository;
	private MemberServiceV2 memberService;
	
	@BeforeEach
	void before() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
		memberRespository = new MemberRepositoryV2(dataSource);
		memberService     = new MemberServiceV2(dataSource,memberRespository);
	}
	
	@AfterEach
	void after() throws SQLException {
		memberRespository.delete(MEMBER_A);
		memberRespository.delete(MEMBER_B);
		memberRespository.delete(MEMBER_EX);
	}
	
	@Test
	@DisplayName("정상 이체")
	void accountTransfer() throws SQLException {
		// given
		Member memberA = new Member(MEMBER_A, 10000);
		Member memberB = new Member(MEMBER_B, 10000);
		memberRespository.save(memberA);
		memberRespository.save(memberB);
		
		// when
		memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);
		
		// then
		Member findMemberA = memberRespository.findById(memberA.getMemberId());
		Member findMemberB = memberRespository.findById(memberB.getMemberId());
		assertThat(findMemberA.getMoney()).isEqualTo(8000);
		assertThat(findMemberB.getMoney()).isEqualTo(12000);
	}
	
	@Test
	@DisplayName("이체중 예외 발생")
	void accountTransferEx() throws SQLException {
		// given : 데이터를 저장해 테스트를 준비
		Member memberA  = new Member(MEMBER_A, 10000);
		Member memberEx = new Member(MEMBER_EX, 10000);
		memberRespository.save(memberA);
		memberRespository.save(memberEx);
		
		// when : 계좌 이체 로직을 실행
		assertThatThrownBy(() -> memberService.accountTransfer(memberA.getMemberId(),
															   memberEx.getMemberId(), 
															   2000))
							.isInstanceOf(IllegalStateException.class);
		
		// then : 계좌 이체 실패, memberA의 돈만 2000원 줄어듦
		Member findMemberA  = memberRespository.findById(memberA.getMemberId());
		Member findMemberEx = memberRespository.findById(memberEx.getMemberId());
		
		// memberA의 돈만 2000원 줄었고 ex의 돈은 10000원 그대로 이다.
		assertThat(findMemberA.getMoney()).isEqualTo(8000);
		assertThat(findMemberEx.getMoney()).isEqualTo(10000);
	}
}
