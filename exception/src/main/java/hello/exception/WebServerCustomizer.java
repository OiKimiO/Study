package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

// 서블릿 오류 페이지 등록
@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{
	
	private final String errorPage = "/error-page";
	
	// 에러 페이지 필터에 등록
	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		
		ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, 
											   errorPage+"/404");
		ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, 
											   errorPage+"/500");
		ErrorPage errorPageEx  = new ErrorPage(RuntimeException.class, 
											   errorPage+"/500");
		
		factory.addErrorPages(errorPage404,
							  errorPage500,
							  errorPageEx);
	}

}
