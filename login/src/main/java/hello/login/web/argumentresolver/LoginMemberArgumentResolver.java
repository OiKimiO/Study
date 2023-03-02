package hello.login.web.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import hello.login.SessionConst;
import hello.login.domain.member.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver{
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("supportsParameter 실행");
		// @Login 애노테이션이 있으면서
		boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
		// Member 타입이면 
		boolean hasMemberType      = Member.class.isAssignableFrom(parameter.getParameterType());
		
		// ArgumentResolver가 사용됨
		return hasLoginAnnotation && hasMemberType;
		// return true;
	}

	// 컨트롤러 호출 직전에 호출되어 필요한 파라미터 정보를 생성
	@Override
	public Object resolveArgument(MethodParameter parameter, 
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, 
								  WebDataBinderFactory binderFactory) throws Exception {
		log.info("resolveArgument 실행");
		
		//
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		// 
		HttpSession session        = request.getSession(false);
		
		if(session == null) {
			return null;
		}
		
		return session.getAttribute(SessionConst.LOGIN_MEMBER);
	}

	
}
