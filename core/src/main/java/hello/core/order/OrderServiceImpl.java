package hello.core.order;

import hello.core.member.*;

import org.springframework.beans.factory.annotation.Autowired;

import hello.core.discount.*;

public class OrderServiceImpl implements OrderService{
	
	
	/*
		
		 현재는 역할(DiscountPolicy)뿐만이 아니라 구현체(FixDicountPolicy)에 까지 의존을 하고 있다.
		 그렇기 때문에 RateDicountPolicy를 추가 하기 위해서는 DiscountPolicy타입과 RateDiscountPolicy 객체를 한번 더 추가해야한다.
		 결론적으로 지금 방식의 코드는 기능의 확장을 위해서는 클라이언트 소스의 변경이 이루어짐으로 
		 OCP(Open Close Principle)의 원칙을 위반한다.
		 또한 클라이언트 소스가 역할과 구현체에 의존하고 있음으로 DIP(Dependency Inversion Principle)을 위반한다.
		 
		private final MemberRepository memberRepository = new MemoryMemberRepository();
		private final DiscountPolicy   discountPolicy   = new FixDiscountPolicy();
		private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	*/
	/*
	private MemberRepository memberRepository;
	private DiscountPolicy   discountPolicy;
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository; 
	}
	
	@Autowired
	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		this.discountPolicy = discountPolicy; 
	}
	
	*/
	
	//  생성자를 이용한 DI
	private final MemberRepository memberRepository;
	private final DiscountPolicy   discountPolicy;
	
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy   = discountPolicy;
	}
	
	
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice,discountPrice);
	}

}
