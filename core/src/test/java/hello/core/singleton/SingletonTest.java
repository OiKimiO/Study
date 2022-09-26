package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.MemberService;
// import hello.core.singleton.SingletonContainer;

public class SingletonTest {
	
	@Test
	@DisplayName("서로 다른 두 객체를 비교한다.")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		MemberService memberService1 = appConfig.memberService();
		MemberService memberService2 = appConfig.memberService();
		
		// 서로 다른 객체를 비교한다.
		assertThat(memberService1).isNotSameAs(memberService2);
	}
	
	@Test
	@DisplayName("싱글톤의 두 객체를 비교한다.")
	void singletonContainer() {
		SingletonContainer singletonContainer1 = SingletonContainer.getInstance();
		SingletonContainer singletonContainer2 = SingletonContainer.getInstance();
		// MemberService memberService1 = appConfig.memberService();
		// MemberService memberService2 = appConfig.memberService();
		
		//System.out.println("singletonContainer1 = " + singletonContainer1);
		// System.out.println("singletonContainer2 = " + singletonContainer2);
		
		// 서로 다른 객체를 비교한다.
		assertThat(singletonContainer1).isSameAs(singletonContainer2);
		System.out.println(singletonContainer1.method1());
		
	}
	
	
	
	
}
