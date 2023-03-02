package io.security.corespringsecurity.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AJaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMessage = "Invalid Username or Password";	
		
		// 응답 헤더에 권한 값과 contentType을 json 형태로 변환시킴
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		if(exception instanceof BadCredentialsException) {
			errorMessage = "Invalid Username or Password";
		}else if(exception instanceof InsufficientAuthenticationException) {
			errorMessage = "Invalid Secret key";
		}else if(exception instanceof CredentialsExpiredException) {
			errorMessage = "Expired Password";
		}
		
		// json 형태로 전달한다.
		objectMapper.writeValue(response.getWriter(), errorMessage);
		
		
	} 
	
	

}
