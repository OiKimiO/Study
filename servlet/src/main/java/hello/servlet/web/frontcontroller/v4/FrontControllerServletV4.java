package hello.servlet.web.frontcontroller.v4;

import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

	private Map<String,ControllerV4> controllerMap = new HashMap<>();
	
	public FrontControllerServletV4() {
		controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		ControllerV4 controller = controllerMap.get(requestURI);
		
		if(controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Map<String,String> paramMap = createParamMap(request);
		Map<String,Object> model    = new HashMap<>();
		
		String viewName = controller.process(paramMap, model);
		
		/* 컨트롤러가 반환한 논리 뷰 이름을 실제 논리 뷰 경로로 변경한다. 
		   그리고 실제 물리 경로가 있는 MyView객체를 반환
			1. 논리 뷰 이름 : members
			2. 물리 뷰 이름 : /WEB-INF/views/members.jsp
		*/
		MyView view = viewResolver(viewName);
		view.render(model,request, response);
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
