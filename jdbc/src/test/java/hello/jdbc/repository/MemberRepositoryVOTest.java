package hello.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryVOTest {

	MemberRespositoryVO repository = new MemberRespositoryVO();
	
	@Test
	void save() throws SQLException {
		// save
		Member member = new Member("memberVO1", 10000);
		repository.save(member);
		
		// findById
		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember = {}",findMember);
		log.info("member == findMember {}", member == findMember);
		log.info("member equals findMember {}", member.equals(findMember));
		assertThat(findMember).isEqualTo(member);
	}
}
