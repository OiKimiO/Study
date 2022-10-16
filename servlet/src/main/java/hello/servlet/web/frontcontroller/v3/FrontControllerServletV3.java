package hello.servlet.web.frontcontroller.v3;

import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

	private Map<String,ControllerV3> controllerMap = new HashMap<>();
	
	public FrontControllerServletV3() {
		controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerMap.put("/front-controller/v3/members/save",     new MemberSaveControllerV3());
		controllerMap.put("/front-controller/v3/members",          new MemberListControllerV3());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV3.service");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI = " + requestURI);
		
		ControllerV3 controller = controllerMap.get(requestURI);
		System.out.println("controller = " + controller);
		
		
		if(controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		
		Map<String,String> paramMap = createParamMap(request);
		ModelView mv 				= controller.process(paramMap);
		
		String viewName = mv.getViewName();
		/* 컨트롤러가 반환한 논리 뷰 이름을 실제 논리 뷰 경로로 변경한다. 
		   그리고 실제 물리 경로가 있는 MyView객체를 반환
			1. 논리 뷰 이름 : members
			2. 물리 뷰 이름 : /WEB-INF/views/members.jsp
		*/
		MyView view = viewResolver(viewName);
		view.render(mv.getModel(),request, response);
	}
	
	private Map<String,String> createParamMap(HttpServletRequest request){
		Map<String,String> paramMap = new HashMap<>();
		
		request.getParameterNames().asIterator()
			   .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp"); 
	}
}
