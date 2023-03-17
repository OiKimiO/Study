package hello.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 예외 누수 문제 해결
 * 체크 예외를 런타임 예외로 변경
 * MemberRepository 인터페이스 사용
 * throws SQLException 제거
 * */
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryV4_1 implements MemberRepository{

	private final DataSource dataSource;
	
	public Member save(Member member){
		String sql = "insert into member(member_id, money) values(?,?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con   = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,member.getMemberId());
			pstmt.setInt(2,member.getMoney());
			pstmt.executeUpdate();
			return member;
		} catch (SQLException e) {
			log.error("db error" , e);
			throw new MyDbException(e);
		}finally {
			close(con, pstmt, null);
		}
	}
	
	public Member findById(String memberId) {
		String sql = "select * from member where member_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMoney(rs.getInt("money"));
				return member;
			}else {
				throw new NoSuchElementException("member not found Exception = "+memberId);
			}
		}catch (SQLException e) {
			log.error("db error" , e);
			throw new MyDbException(e);
		}finally {
			close(con, pstmt, rs);
		}
				
	}
	
	public void update(String memberId, int money) {
		String sql = "update member set money = ? where member_id=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con   = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,money);
			pstmt.setString(2,memberId);
			// 수행된 결과의 갯수
			int resultSize = pstmt.executeUpdate();
			log.info("resultsize={}", resultSize);
		} catch (SQLException e) {
			log.error("db error" , e);
			throw new MyDbException(e);
		}finally {
			close(con, pstmt, null);
		}
	}
	
	
	public void delete(String memberId){
		String sql = "delete from member where member_id=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con   = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,memberId);
			// 수행된 결과의 갯수
			int resultSize = pstmt.executeUpdate();
			log.info("resultsize={}", resultSize);
		} catch (SQLException e) {
			log.error("db error" , e);
			throw new MyDbException(e);
		}finally {
			close(con, pstmt, null);
		}
	}
	
	
	// 리소스를 반환할 때는 역순으로 종료
	private void close(Connection con, 
					   Statement stmt, 
					   ResultSet rs) {
		// Connection의 연결을 종료
		// 종료하는 것이 Pool에 Connection을 반환하는 것을 말함
		JdbcUtils.closeResultSet(rs);
		JdbcUtils.closeStatement(stmt);
		
		// 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 함
		DataSourceUtils.releaseConnection(con, dataSource);
	}
	
	public Connection getConnection() throws SQLException {
		// 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 함
		// DataSource에서 Connection 들고오면 Conection autoCommit으로 처리됨
		Connection con = DataSourceUtils.getConnection(dataSource);
		log.info("get Connection ={}, class={}",con, con.getClass());
		return con;
	}
}
