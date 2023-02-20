package hello.login.web.session;

import java.util.concurrent.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import org.springframework.stereotype.Component;



/**
 * 세션 관리
 * */
@Component
public class SessionManager {

	public static final String SESSION_COOKIE_NAME="mySessionId";
	
	// HashMap은 동시요청에 안전하지 않아 ConcurrentHashMap을 사용
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
	
	/**
	 * 세션 생성
	 * @param Object sessionStore value 값
	 * @param HttpServletResponse cookie를 저장할 응답 값
	 * */
	public void createSession(Object value, HttpServletResponse response) {
		
		// 세션 id를 생성하고, 값을 세션에 저장
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, value);
		
		// 쿠키 생성 							name,value
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME,sessionId);
		response.addCookie(mySessionCookie);
	}
	
	/**
	 * 세션 조회
	 * @return null or sessionStore value
	 * */
	public Object getSession(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request,SESSION_COOKIE_NAME);
		
		if(sessionCookie == null) {
			return null;
		}
		//                        sessionId
		return sessionStore.get(sessionCookie.getValue());
	}

	/**
	 * 세션 만료
	 * */
	public void expire(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie != null) {
			sessionStore.remove(sessionCookie.getValue());
		}
	}
	
	private Cookie findCookie(HttpServletRequest request, String cookieName) {
		if(request.getCookies() == null) {
			return null;
		}
		
		return Arrays.stream(request.getCookies())
					 .filter(cookie -> cookie.getName().equals(cookieName))
					 .findAny()
					 .orElse(null);
	}

	
}
