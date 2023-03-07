package hello.exception;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;

/* DispatcherType
	REQUEST : 클라이언트 요청
	ERROR   : 오류 요청
	FORWARD : MVC에서 배웠던 서블릿에서 다른 서블릿이나 JSP를 호출할 때
	 - RequestDispatcher.forward(request, response);
	INCLUDE : 서블릿에서 다른 서블릿이나 JSP의 결과를 포함할 때
	 - RequestDispatcher.include(request, response);
	ASYNC   : 서블릿 비동기 호출
 * 
 * */

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**",
									 "/*.ico",
									 "/error",
									 "/error-page/*");
	}
	
	// @Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		// 클라이언트 요청 및 에러 페이지 요청에도 필터가 호출
		filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ERROR);
		
		return filterRegistrationBean;
	}
}
