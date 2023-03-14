package hello.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV0Test {

	MemberRepositoryV0 repository = new MemberRepositoryV0();
	
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
