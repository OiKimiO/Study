package io.security.corespringsecurity.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response, 
										AuthenticationException exception)
			throws IOException, ServletException {
		
		log.info("failure onAuthenticationFailure 도착");
		
		String errorMessage = "Invalid Username or Password";
		
		if(exception instanceof BadCredentialsException) {
			errorMessage = "Invalid Username or Password";
		}else if(exception instanceof InsufficientAuthenticationException) {
			errorMessage = "Invalid Secret key";
		}
		
		setDefaultFailureUrl("/login?error=true&exception="+errorMessage);
		// log.info("");
		super.onAuthenticationFailure(request, response, exception);
	}
}