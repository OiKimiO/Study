package hello.jdbc.repository;

import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static hello.jdbc.connection.ConnectionConst.URL;
import static hello.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV1Test {

	MemberRepositoryV1 repository;
	
	@BeforeEach
	void beforeEach() throws InterruptedException {
		// 기본 DriverManager - 항상 새로운 Connection 획득
		// DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME,PASSWORD);
		
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		
		repository = new MemberRepositoryV1(dataSource);
		Thread.sleep(1000);
	}
	
	@Test
	void save() throws SQLException {
		// save
		Member member = new Member("memberV20", 10000);
		repository.save(member);
		
		// findById
		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember = {}",findMember);
		log.info("member == findMember {}", member == findMember);
		
		// log.info("member equals findMember {}", member.equals(findMember));
		assertThat(findMember).isEqualTo(member);
		
		// update 
		repository.update(member.getMemberId(), 20000);
		Member updateMember = repository.findById(member.getMemberId());
		assertThat(updateMember.getMoney()).isEqualTo(20000);
		
		//delete
		repository.delete(member.getMemberId());
		
		// 삭제 검증하는 방법
		assertThatThrownBy(() -> repository.findById(member.getMemberId()))
						  .isInstanceOf(NoSuchElementException.class);
		// Member deleteMember = repository.findById(member.getMemberId());
	}
}
