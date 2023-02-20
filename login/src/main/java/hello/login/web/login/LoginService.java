package hello.login.web.login;

import org.springframework.stereotype.Service;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;
	
	/** 
	 * @return null이면 로그인 실패 
	 * */
	public Member login(String loginId, String password) {
		log.info("memberRepository : {}",memberRepository);
		
		return memberRepository.findByLoginId(loginId)
							   .filter(m->m.getPassword().equals(password))
							   .orElse(null);
	}
	
	
}
