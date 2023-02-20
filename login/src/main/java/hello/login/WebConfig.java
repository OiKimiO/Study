package hello.login;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**",
									 "/*.ico",
									 "/error");
		
		registry.addInterceptor(new LoginCheckInterceptor())
				.order(2)
				.addPathPatterns("/**")
				.excludePathPatterns("/",
									 "/members/add",
									 "/login",
									 "/logout",
									 "/css/**",
									 "/*.ico",
									 "/error");
	}
	
	// @Bean
	public FilterRegistrationBean logFilter() {
		// 
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		// 등록할 필터를 지정
		filterRegistrationBean.setFilter(new LogFilter());
		
		// 필터는 체인으로 동작하며, 낮은 순으로 먼저 동작
		filterRegistrationBean.setOrder(1);
		// 필터에 적용할 URL 패턴 지정
		filterRegistrationBean.addUrlPatterns("/*");
		
		return filterRegistrationBean;
	}
	
	// @Bean
	public FilterRegistrationBean loginCheckFilter() {
		// 
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		// 등록할 필터를 지정
		filterRegistrationBean.setFilter(new LoginCheckFilter());
		
		// 필터는 체인으로 동작하며, 낮은 순으로 먼저 동작
		// 순번을 2번으로 잡았으며, 로그 필터 다음에 로그인 필터가 작동됨
		filterRegistrationBean.setOrder(2);
		
		// 필터에 적용할 URL 패턴 지정
		filterRegistrationBean.addUrlPatterns("/*");
		
		return filterRegistrationBean;
	}
}
