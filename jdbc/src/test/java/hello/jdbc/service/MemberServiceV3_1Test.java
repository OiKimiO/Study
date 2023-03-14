package hello.jdbc.service;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
/**
 *  트랜잭션 - 트랜잭션 매니저
 * */
public class MemberServiceV3_1Test {

	public static final String MEMBER_A  = "memberA";
	public static final String MEMBER_B  = "memberB";
	public static final String MEMBER_EX = "ex";
	
	private MemberRepositoryV3 memberRespository;
	private MemberServiceV3_1 memberService;
	
	@BeforeEach
	void before() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		memberRespository = new MemberRepositoryV3(dataSource);
		memberService     = new MemberServiceV3_1(transactionManager,memberRespository);
	}
	
	// @AfterEach
	void after() throws SQLException {
		memberRespository.delete(MEMBER_A);
		memberRespository.delete(MEMBER_B);
		memberRespository.delete(MEMBER_EX);
	}
	
	/*
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
	}*/
	
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
		
		// PlatformTransactionManager로 Connection을 관리한 뒤에는 처리 과정에서 에러가 발생하면
		// 트랜잭션을 rollback 처리
		assertThat(findMemberA.getMoney()).isEqualTo(10000);
		assertThat(findMemberEx.getMoney()).isEqualTo(10000);
	}
}
