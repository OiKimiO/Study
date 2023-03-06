package io.security.corespringsecurity.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response, 
										Authentication authentication)
			throws IOException, ServletException {
		
		log.info("suceess onAuthenticationSuccess 도착");
		setDefaultTargetUrl("/");
		
		// 사용자의 요청정보
		SavedRequest saveRequest = requestCache.getRequest(request, response);
		
		if(saveRequest != null) {
			String targetUrl = saveRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}else {
			redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
		}
		
	}
}