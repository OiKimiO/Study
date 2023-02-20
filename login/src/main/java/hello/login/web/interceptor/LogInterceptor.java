package hello.login.web.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

	public static final String LOG_ID = "logId";
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		// 요청 로그를 구분하기 위한 uuid를 생성
		String uuid = UUID.randomUUID().toString();
		
		// 스프링 인터셉터는 호출시점이 완전히 분리되어 있어, postHandle, afterCompletion에서 함께 사용하려면 
		// 어딘가에 담아두어야함
		request.setAttribute(LOG_ID, uuid);
		
		// @RequestMapping : HandlerMethod
		// 정적 리소스 : ResourceHttpRequestHandler
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler; // 호출할 컨트롤러 메서드의 모든 정보가 포함
		}
		
		log.info("REQUEST [{}][{}][{}]",uuid, requestURI,handler);
		
		// false 진행 X
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
						   HttpServletResponse response, 
						   Object handler,
						   @Nullable ModelAndView modelAndView) throws Exception {
		log.info("postHandle [{}]",modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler,
								@Nullable Exception ex) throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String)request.getAttribute(LOG_ID);
		log.info("RESPONSE [{}][{}]",logId,requestURI);
		
		if(ex != null) {
			log.error("afterCompletion error!!",ex);
		}
	}
}
