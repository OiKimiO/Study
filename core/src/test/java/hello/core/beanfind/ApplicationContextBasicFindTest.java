package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;



public class ApplicationContextBasicFindTest {
	// assertThat   : 객체의 값을 비교한다. 
	// isInstanceOf : java instanceof 연산자 는 객체가 지정된 유형(클래스 또는 하위 클래스 또는 인터페이스)의 인스턴스인지 여부를 테스트하는 데 사용됩니다 .
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService",MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("이름 없는 타입만으로 조회")
	void fundBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class); 
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	
	// 구체 타입으로 조회하면 변경시 유연성이 떨어진다.
	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2() {
		MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 이름으로 조회X")
	void findBeanByNameX() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx",MemberService.class));
	}
}
