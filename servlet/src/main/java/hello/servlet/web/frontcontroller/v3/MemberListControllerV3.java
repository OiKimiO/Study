package hello.servlet.web.frontcontroller.v3;

import java.util.*;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;

public class MemberListControllerV3 implements ControllerV3{

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paramMap) {
		List<Member> members = memberRepository.findAll();
		
		ModelView mv = new ModelView("members");
				  mv.getModel().put("members", members);
				  
		return mv;
	}
	
}

