package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;

public class MemberFormControllerV4 implements ControllerV4{

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		return "new-form";
	}

}
