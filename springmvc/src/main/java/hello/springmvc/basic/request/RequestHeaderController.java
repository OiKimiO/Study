package hello.springmvc.basic.request;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RequestHeaderController {
	
	
	/**
	 * @param 
	 *   - HttpMethod : HTTP 메서드를 조회한다. org.springframework.http.HttpMethod
	 *   - Locale     : Locale 정보를 조회한다.
	 *   - @RequestHeader MultiValueMap<String, String> headerMap
	 *     * 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다
	 *   - @RequestHeader("host") String host
	 *     * 특정 HTTP 헤더를 조회한다.
		   * 속성
			 1) 필수 값 여부 : required
			 2) 기본 값 속성 : defaultValue
	     - @CookieValue(value = "myCookie", required = false) String cookie
	       * 특정 쿠키를 조회한다. 
	       * 속성
			 1) 필수 값 여부 : required
			 2) 기본 값 속성 : defaultValue
	 * */
	@RequestMapping("/headers")
	public String headers( HttpServletRequest  request,
						   HttpServletResponse response,
						   HttpMethod 		   httpMethod,
						   Locale 			   locale,
						   @RequestHeader MultiValueMap<String, String> headerMap,
						   @RequestHeader("host") String host,
						   @CookieValue(value="myCookie", required = false) String cookie
						) {
		// MultiValueMap : 키하나에 여러 값을 받을 수 있다.
		log.info("request={}",request);
		log.info("response={}",response);
		log.info("httpMethod={}",httpMethod);
		log.info("locale={}",locale);
		log.info("headerMap={}",headerMap);
		log.info("host={}",host);
		log.info("cookie={}",cookie);
		
		return "ok";
	}
}
