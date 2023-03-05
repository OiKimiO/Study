package io.security.corespringsecurity.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import io.security.corespringsecurity.security.common.AjaxLoginAuthenticationEntryPoint;
import io.security.corespringsecurity.security.handler.AJaxAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.AJaxAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.handler.AjaxAccessDeniedHandler;
import io.security.corespringsecurity.security.provider.AjaxAuthenticationProvider;

@Configuration
@Order(1)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(AjaxAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider AjaxAuthenticationProvider() {
		return new AjaxAuthenticationProvider();
	}

	@Bean
	public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
		return new AJaxAuthenticationSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
		return new AJaxAuthenticationFailureHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/api/**")
				.authorizeRequests()
				.antMatchers("/api/messages").hasRole("MANAGER")
				.anyRequest().authenticated()
	   /*.and()
	        	.addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)*/
	        ;		
	   http
	   			.exceptionHandling()
	   			.authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
	   			.accessDeniedHandler(ajaxAccessDeniedHandler());
	   //	        http.csrf().disable();
	        
	   customConfigurationAjax(http);
				
	}
	
	private void customConfigurationAjax(HttpSecurity http) throws Exception {
		http
				.apply(new AjaxLoginConfigurer<>())
				.successHandlerAjax(ajaxAuthenticationSuccessHandler())
				.failureHandlerAjax(ajaxAuthenticationFailureHandler())
				.setauthenticationManager(authenticationManagerBean())
				.loginProcessingUrl("/api/login");
	}

	@Bean
	public AccessDeniedHandler ajaxAccessDeniedHandler() {
		return new AjaxAccessDeniedHandler();
	}
/*
	@Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
    	AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
    	ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
    	ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
    	ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
    	return ajaxLoginProcessingFilter;
    }*/
	
}
