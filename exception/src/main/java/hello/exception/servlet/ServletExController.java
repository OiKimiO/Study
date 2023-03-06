package hello.exception.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ServletExController {

	@GetMapping("/error-ex")
	public void errorEx() {
		throw new RuntimeException("예외 발생!");
	}
	
	@GetMapping("/error-404")
	public void error404(HttpServletResponse response) throws IOException {
		// sendError() 흐름 : WAS(sendError 호출 기록 확인) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러
		response.sendError(404,"404 오류!");
	}
	
	@GetMapping("/error-500")
	public void error500(HttpServletResponse response) throws IOException {
		response.sendError(500,"500 오류!");
	}
}
