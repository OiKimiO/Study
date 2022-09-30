package hello.core.scope;

import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.*;

public class SingletonTest {

	@Test
	void singletonBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
		SingletonBean singletonbean1 = ac.getBean(SingletonBean.class);
		SingletonBean singletonbean2 = ac.getBean(SingletonBean.class);
		System.out.println("singletonbean1 = " + singletonbean1);
		System.out.println("singletonbean2 = " + singletonbean2);
		assertThat(singletonbean1).isSameAs(singletonbean2);
		ac.close();
	}
	
	@Scope("singleton")
	static class SingletonBean{
		
		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}
