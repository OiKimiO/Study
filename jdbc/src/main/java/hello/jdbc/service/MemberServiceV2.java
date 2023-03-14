package hello.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** 
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 * */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {
	
	private final DataSource dataSource;
	private final MemberRepositoryV2 memberRepository;
	
	public void accountTransfer(String fromId, 
								String toId,
								int money) throws SQLException {
		// 커넥션을 조회
		Connection con = dataSource.getConnection();
		
		try {
			// 트랜잭션 시작
			con.setAutoCommit(false); 
			
			// 비즈니스 로직
			// bizLogic(con, fromId, toId, money);
			
			// 성공시 커밋
			con.commit();
		}catch(Exception e) {
			// 실패시 롤백
			con.rollback();
			throw new IllegalStateException(e);
		}finally {
			release(con);
		}
		
	}

	private void release(Connection con) {
		if(con != null) {
			try {
				// 커넥션 풀 고려
				// 현재 수동 커밋모드로 작동하기 떄문에 Connection을 반납하기 전 자동 커밋모드로 변경
				con.setAutoCommit(true);
				
				// 커넥션을 풀에 반납
				con.close();
			}catch(Exception e) {
				log.info("error",e);
			}
		}
	}
	/*
	private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
		Member fromMember = memberRepository.findById(con, fromId);
		Member toMember   = memberRepository.findById(con, toId);
				 
		memberRepository.update(fromId, fromMember.getMoney() - money);
		validation(toMember);
		memberRepository.update(toId, toMember.getMoney() + money);
	}
	 */
	private void validation(Member toMember) {
		if(toMember.getMemberId().equals("ex")) {
			throw new IllegalStateException();
		}
	}
}
