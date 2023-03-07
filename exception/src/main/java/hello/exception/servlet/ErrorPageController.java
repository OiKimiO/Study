package hello.exception.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/error-page")
public class ErrorPageController {
	private final String defaultPage = "error-page";
	
	public static final String ERROR_EXCEPTION      = "javax.servlet.error.exception";
	public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
	public static final String ERROR_MESSAGE        = "javax.servlet.error.message";
	public static final String ERROR_REQUEST_URI    = "javax.servlet.error.request_uri";
	public static final String ERROR_SERVLET_NAME   = "javax.servlet.error.servlet_name";
	public static final String ERROR_SERVLET_CODE   = "javax.servlet.error.servlet_code";
	
	@RequestMapping("/404")
	public String errorPage404(HttpServletRequest  request,
							   HttpServletResponse response) {
		log.info("errorPage 404");
		printErrorInfo(request);		
		return defaultPage+"/404";
	}

	@RequestMapping("/500")
	public String errorPage500(HttpServletRequest  request,
								HttpServletResponse response) {
		log.info("errorPage 500");
		printErrorInfo(request);
		return defaultPage+"/500";
	}
	
	private void printErrorInfo(HttpServletRequest request) {
		log.info("ERROR_EXCEPTION: ex = {}" , request.getAttribute(ERROR_EXCEPTION));
		log.info("ERROR_EXCEPTION_TYPE: {}" , request.getAttribute(ERROR_EXCEPTION_TYPE));
		log.info("ERROR_MESSAGE: {}"		, request.getAttribute(ERROR_MESSAGE));
		log.info("ERROR_REQUEST_URI: {}"    , request.getAttribute(ERROR_REQUEST_URI));
		log.info("ERROR_SERVLET_NAME: {}"   , request.getAttribute(ERROR_SERVLET_NAME));
		log.info("ERROR_SERVLET_CODE: {}"   , request.getAttribute(ERROR_SERVLET_CODE));
	}
}
