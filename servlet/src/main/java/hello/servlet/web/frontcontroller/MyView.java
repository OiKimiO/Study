package hello.servlet.web.frontcontroller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyView {

	private String viewPath;
	
	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}
	
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
						  dispatcher.forward(request, response);
	}
}
