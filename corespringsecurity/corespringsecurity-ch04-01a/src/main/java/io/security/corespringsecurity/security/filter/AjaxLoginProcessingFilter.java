package io.security.corespringsecurity.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.StringUtils;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.security.token.AjaxAuthenticationToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter{

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public AjaxLoginProcessingFilter() {
		super(new AntPathRequestMatcher("/api/login"));
		
		log.info("해당 URL로 필터를 등록 = /api/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
											    HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("/api/login을 호출하여 인증 시작");
		log.info("사용자가 전송한 계정 정보 = {}", request.getReader());
		
		if(!isAjax(request)) {
			throw new IllegalStateException("Authentication is not supported");
		}
		
		AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);
		
		if(StringUtils.isEmpty(accountDto.getUsername()) || StringUtils.isEmpty(accountDto.getPassword())) {
			throw new IllegalArgumentException("Username or Password is empty");
		}
		
		AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(accountDto.getUsername(),
																					  accountDto.getPassword());
		
		return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
	}

	private boolean isAjax(HttpServletRequest request) {
		// ajax 방식의 요청
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			return true;
		}
		
		return false;
	}

}
