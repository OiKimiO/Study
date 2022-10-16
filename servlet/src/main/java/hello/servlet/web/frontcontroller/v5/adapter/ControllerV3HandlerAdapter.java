package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter{

	
	/** supports
	 *  @param Object hadler
	 *    - ControllerV3를 대처할 수 있는 컨트롤러를 뜻한다. 
	 * */
	@Override
	public boolean supports(Object handler) {
		// instanceof : handler가 ControllerV3 타입이거나 ControllerV3를 상속받는 클래스라면 
		// true를 반환합니다.
		return (handler instanceof ControllerV3);
	}

	/** handle
	 *  @param HttpServletRequest request, HttpServletResponse response, Object handler
	 *   - 
	 * */
	
	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		ControllerV3 controller = (ControllerV3) handler;
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);
		return mv;
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String,String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
		       .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
				
		return paramMap;
	}

}
