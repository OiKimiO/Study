package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class MemberRepositoryTest {

	private static final String List = null;
	MemberRepository memberRepository = MemberRepository.getInstance();
	
	// 각 테스트 메서드가 실행된 후 실행되는 메서드
	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void save() {
		// given
		Member member = new Member("hello",20);
		
		// when
		Member savedMember = memberRepository.save(member);
		
		// then
		Member findMember = memberRepository.findById(savedMember.getId());
		assertThat(findMember).isEqualTo(savedMember);
	}
	
	@Test
	void findAll() {
		// given
		Member member1 = new Member("member1",20);
		Member member2 = new Member("member2",30);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		// when
		List<Member> result = memberRepository.findAll();
		
		// then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result).contains(member1,member2);
	}
}