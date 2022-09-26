package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {
	
	@Test
	void statefulServiceTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService",StatefulService.class);
		
		int UserAPrice = statefulService1.order("UserA", 10000);
		
		int UserBPrice = statefulService2.order("UserB", 20000);
		
		System.out.println(UserAPrice);
	}
	
	@Configuration
	static class TestConfig{
		
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
