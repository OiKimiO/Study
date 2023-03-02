package io.security.corespringsecurity.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

// 사용자들이 인가되지 않은 페이지에 접근할 경우
public class AjaxAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, 
					   HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendError(response.SC_FORBIDDEN,"Access is denied");	
	}

}
