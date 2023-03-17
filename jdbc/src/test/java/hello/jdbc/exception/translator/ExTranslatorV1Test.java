package hello.jdbc.exception.translator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import hello.jdbc.repository.ex.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
import static hello.jdbc.errorcode.settings.DBErrorCodeSettings.*;

public class ExTranslatorV1Test {

	Repository repository;
	Service service;
	
	@Slf4j
	@RequiredArgsConstructor
	static class Service{
		
		private final Repository repository;
		
		public void create(String memberId) {
			try {
				
			}catch(MyDuplicateKeyException e) {
				log.info("키 중복, 복구 시도");
				String retryId = generateNewId(memberId);
				log.info("retryId = {}",retryId);
				repository.save(new Member(retryId, 0));
			}catch(MyDbException e) {
				log.info("데이터 접금 계층 예외", e);
				throw e;
			}
		}

		private String generateNewId(String memberId) {
			return memberId + new Random().nextInt(10000);
		}
	}
	
	@RequiredArgsConstructor
	static class Repository{
		
		private final DataSource dataSource;
		
		public Member save(Member member) {
			String sql = "insert into member(member_id, money) values (?,?)";
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMemberId());
				pstmt.setInt(2, member.getMoney());
				pstmt.executeUpdate();
				return member;
			}catch(SQLException e) {
				// h2 db
				// DB별 DBERROR_CODE를 관리해서 사용
				if(e.getErrorCode() == TEST_DB_ERROR_CODE.DuplicatedPrimaryKeyErrorCode()) {
					throw new MyDuplicateKeyException(e);
				}
				
				throw new MyDbException(e);
			}finally {
				// closeStatement(pstmt);
				// closeConnection(con);
			}
		}
		
	}
}
