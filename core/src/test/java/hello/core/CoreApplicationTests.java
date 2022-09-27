package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

class CoreApplicationTests {

	ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	void contextLoads() {
		
		// setter를 활용한 의존성 주입 테스트
		MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
		OrderService orderService   = ac.getBean("orderService",OrderServiceImpl.class);
		Member member = new Member(1L, "오우", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(1L, "ItemA", 20000);
		System.out.println(order);
		
		/*
		 생성자를 통한 의존성 주입 테스트
		MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
		OrderService orderService   = ac.getBean("orderService",OrderServiceImpl.class);
		Member member = new Member(1L, "오우", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(1L, "ItemA", 20000);
		System.out.println(order);
		*/
	}

}
