
package io.security.corespringsecurity.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.security.corespringsecurity.security.common.FormAuthenticationDetailsSource;
import io.security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import io.security.corespringsecurity.security.handler.CustomAccessDeniedHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.provider.FormAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
@Order(0)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Autowired
	private FormAuthenticationDetailsSource formAuthenticationDetailsSource;
	
	// webIgnore 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
    	/* 정적 자원(/css/*,/js/*,/html/* 등)은 web.ignoring으로 처리하는 것이 맞음
    	 *  => 보안필터를 거치지 않는다.
    	 */
    	web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
    public AuthenticationProvider authenticationProvider() {
		return new FormAuthenticationProvider();
	}

	// 비밀번호를 암호화 시켜줌
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/users","user/login/**","/login*").permitAll() // 모든 사용자가 접근 가능
                .antMatchers("/mypage").hasRole("USER") // USER는 mypage만 접근 가능
                .antMatchers("/messages").hasRole("MANAGER") 
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .defaultSuccessUrl("/")
                .authenticationDetailsSource(formAuthenticationDetailsSource)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()
        .and()
        		.exceptionHandling()
        		.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        		.accessDeniedPage("/denied")
        		.accessDeniedHandler(accessDeniedHandler())
       ;
                
    }

    @Bean
	public AccessDeniedHandler accessDeniedHandler() {
    	CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
		accessDeniedHandler.setErrorPage("/denied");
    	return accessDeniedHandler;
	}
    
    
}
