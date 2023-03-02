package io.security.corespringsecurity.security.handler;

import org.springframework.http.MediaType;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.security.corespringsecurity.domain.entity.Account;

public class AJaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Account account = (Account) authentication.getPrincipal();
		
		// json 형태로 리턴한다.
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		objectMapper.writeValue(response.getWriter(), account);
	}

}
