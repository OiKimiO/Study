package hello.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRespositoryV0 {

	public Member save(Member member) throws SQLException{
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
			throw e;
		}finally {
			close(con, pstmt, null);
		}
	}
	
	public Member findById(String memberId) throws SQLException {
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
			throw e;
		}finally {
			close(con, pstmt, rs);
		}
				
	}
	
	public void update(String memberId, int money) throws SQLException {
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
			throw e;
		}finally {
			close(con, pstmt, null);
		}
	}
	
	
	public void delete(String memberId) throws SQLException {
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
			throw e;
		}finally {
			close(con, pstmt, null);
		}
	}
	
	
	// 리소스를 반환할 때는 역순으로 종료
	private void close(Connection con, 
					   Statement pstmt, 
					   ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close(); // SQLException
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		
	}
	
	public Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}
}
