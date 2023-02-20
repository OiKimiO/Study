package hello.login.web.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/* 1. 필터 사용을 위해 Filter 인터페이스를 구현
 * 
 * */

@Slf4j
public class LogFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig)
			throws ServletException {
		log.info("log filter init");
	}
	
	/**
	 * 
	 * */
	@Override
	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		// UUID 
		String uuid = UUID.randomUUID().toString();
		
		try {
			log.info("REQUEST [{}][{}]",uuid,requestURI);
			// 다음 필터가 있으면 필터를 호출하고, 필터가 없으면 서블릿을 호출
			chain.doFilter(request, response);
		}catch(Exception e) {
			throw e;
		}finally {
			log.info("RESPONSE [{}][{}]",uuid,requestURI);
		}
	}
	
	@Override
	public void destroy() {
		log.info("log filter destroy");
	}

}
