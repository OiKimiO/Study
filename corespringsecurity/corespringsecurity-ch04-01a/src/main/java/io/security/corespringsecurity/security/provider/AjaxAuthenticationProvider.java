package io.security.corespringsecurity.security.provider;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.security.corespringsecurity.security.common.FormWebAuthenticationDetails;
import io.security.corespringsecurity.security.service.AccountContext;
import io.security.corespringsecurity.security.token.AjaxAuthenticationToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AjaxAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// 검증을 위한 구현
	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("authenticate 시작");
		log.info("사용자가 전달한 user = {}",authentication);
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		AccountContext accountContext =  (AccountContext) userDetailsService.loadUserByUsername(username);
		
		if(!passwordEncoder.matches(password, accountContext.getPassword())) {
			throw new BadCredentialsException("BadCredentialsException");
		}
		/*
		FormWebAuthenticationDetails formWebAuthenticationDetails = (FormWebAuthenticationDetails)authentication.getDetails();
		String secretKey = formWebAuthenticationDetails.getSecretKey();
		
		if(secretKey == null || !"secret".equals(secretKey)) {
			throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
		}
		*/
		
		return new AjaxAuthenticationToken(accountContext.getAccount(), 
										   null, 
										   accountContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(AjaxAuthenticationToken.class);
	}

}
