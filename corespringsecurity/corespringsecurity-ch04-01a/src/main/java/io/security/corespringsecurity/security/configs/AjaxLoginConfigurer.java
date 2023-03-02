package io.security.corespringsecurity.security.configs;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import io.security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import lombok.extern.slf4j.Slf4j;


/* Custom DSLs
 *  - 스프링 시큐리티 초기화 설정 클래스
 *  - 필터, 핸들러, 메서드, 속성 등을 한 곳에 정의하여 처리할 수 있는 편리한 제공
*/
@Slf4j
public class AjaxLoginConfigurer<H extends HttpSecurityBuilder<H>>
extends AbstractAuthenticationFilterConfigurer<H, AjaxLoginConfigurer<H>, AjaxLoginProcessingFilter>{

	private AuthenticationSuccessHandler successHandler;
	private AuthenticationFailureHandler failureHandler;
	private AuthenticationManager authenticationManager;
	
	public AjaxLoginConfigurer() {
		super(new AjaxLoginProcessingFilter(),null);
	}
	
	// 초기화
	@Override
	public void init(H http) throws Exception {		
		super.init(http);
	}
	
	// 설정
	@Override
	public void configure(H http) throws Exception {
		log.info("Type H ::: ", http);
		
		if(authenticationManager == null) {
			authenticationManager = http.getSharedObject(AuthenticationManager.class);
		}
		
		getAuthenticationFilter().setAuthenticationManager(authenticationManager);
		getAuthenticationFilter().setAuthenticationSuccessHandler(successHandler);
		getAuthenticationFilter().setAuthenticationFailureHandler(failureHandler);
		
		SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
		
		if(sessionAuthenticationStrategy != null) {
			getAuthenticationFilter().setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
		}
		
		RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
		
		if(rememberMeServices != null) {
			getAuthenticationFilter().setRememberMeServices(rememberMeServices);
		}
		
		http.setSharedObject(AjaxLoginProcessingFilter.class, getAuthenticationFilter());
		http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	public AjaxLoginConfigurer<H> successHandlerAjax(AuthenticationSuccessHandler successHandler){
		this.successHandler = successHandler;
		return this;
	}
	
	public AjaxLoginConfigurer<H> failureHandlerAjax(AuthenticationFailureHandler failureHandler){
		this.failureHandler = failureHandler;
		return this;
	}
	
	public AjaxLoginConfigurer<H> setauthenticationManager(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
		return this;
	}
	
	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		log.info("createLoginProcessingUrlMatcher = {}", loginProcessingUrl);
		return new AntPathRequestMatcher(loginProcessingUrl);
	}

}
