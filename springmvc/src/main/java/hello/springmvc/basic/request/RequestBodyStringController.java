package hello.springmvc.basic.request;

import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {

	@PostMapping("/request-body-string-v1")
	public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		
		log.info("messageBody={}",messageBody);
		response.getWriter().write("ok");
	}
	
	/**
	 * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
	 * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
	 */
	@PostMapping("/request-body-string-v2")
	public void requestBodyString(InputStream inputStream, Writer responseWriter) throws IOException {
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}",messageBody);
		responseWriter.write("ok");
	}
}
