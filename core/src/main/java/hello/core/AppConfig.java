package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {
	// 각 메서드에 @Bean을 붙여준다. 이렇게 하면 스프링 컨테이너에 스피링 빈으로 등록한다.
	
	@Bean
	public MemberService memberService() {
		// 기능을 사용할 구현체에 기능이 정의된 메서드를 매개변수로 던진다.
		return new MemberServiceImpl(memberRepository());
	}
	
	
	@Bean
	public OrderService orderService() {
		/*
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.setDiscountPolicy(discountPolicy());
		orderServiceImpl.setMemberRepository(memberRepository());
		return orderServiceImpl;
		*/
		
		// 기능을 사용할 구현체에 기능이 정의된 메서드를 매개변수로 던진다.
		
		return new OrderServiceImpl(
									memberRepository(),
									discountPolicy()
				);
		
	}
	
	// 새로운 기능이 정의되면 아래의 return할 객체를 변경한다.
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	
	// 새로운 기능이 정의되면 아래의 return할 객체를 변경한다.
	@Bean
	public DiscountPolicy discountPolicy() {
		// return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
	
}