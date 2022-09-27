package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import hello.core.scan.filter.*;

public class ComponentFilterAppConfigTest {
	
	/*
	@Test
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ac.getBean("beanA",BeanA.class);
		assertThat(beanA).isNotNull();
		
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, 
								()->ac.getBean("beanB",BeanB.class));
	}
	
	@Configuration
	@ComponentScan(
					includeFilters=@Filter(type=FilterType.ANNOTATION,classes=MyIncludeFilter.class),
					excludeFilters=@Filter(type=FilterType.ANNOTATION,classes=MyExcludeComponent.class))
	static class ComponentFilterAppConfig{
		
	}
	*/
}
