package hello.login.web.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.login.SessionConst;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	private final SessionManager sessionManager = new SessionManager(); 
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		log.info("login page start");
		return "login/loginForm";
	}
	
	/*
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm form,
						BindingResult bindingResult,
						HttpServletResponse response) {
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		
		Member loginMember = loginService.login(form.getLoginId(), 
												form.getPassword());
		log.info("login? {}", loginMember);
		
		if(loginMember == null) {
			bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		// 로그인 성공 처리
		// 쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 정료시 모두 종료)
		Cookie idCookie = new Cookie("memberId",
									 String.valueOf(loginMember.getId()));
		
		// 로그인에 성공하면 쿠키를 생성하고 HttpServletResponse에 담는다.
		response.addCookie(idCookie);
		
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String loginV2(@Valid @ModelAttribute LoginForm form,
						BindingResult bindingResult,
						HttpServletResponse response) {
		
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		
		Member loginMember = loginService.login(form.getLoginId(), 
												form.getPassword());
		log.info("login? {}", loginMember);
		
		if(loginMember == null) {
			bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		// 로그인 성공 처리
		// 세션 관리자를 통해 세션을 관리하고, 회원 데이터를 보관
		sessionManager.createSession(loginMember, response);
		
		return "redirect:/";
	}
	
	
	@PostMapping("/login")
	public String loginV3(@Valid @ModelAttribute LoginForm form,
						  BindingResult bindingResult,
						  HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		
		Member loginMember = loginService.login(form.getLoginId(), 
												form.getPassword());
		log.info("login? {}", loginMember);
		
		if(loginMember == null) {
			bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		// 로그인 성공 처리
		// 세션이 있으면 있는 세션 반환, 없으면 신규 세션 반환 
		// request.getSession() == request.getSession(true) 
		HttpSession session = request.getSession();
		
		// 세션에 로그인 회원 정보 보관 
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		return "redirect:/";
	}
	*/
	
	@PostMapping("/login")
	public String loginV4(@Valid @ModelAttribute LoginForm form,
						  BindingResult bindingResult,
						  @RequestParam(defaultValue="/") String redirectURL,
						  HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		
		Member loginMember = loginService.login(form.getLoginId(), 
												form.getPassword());
		log.info("login? {}", loginMember);
		
		if(loginMember == null) {
			bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		// 로그인 성공 처리
		// 세션이 있으면 있는 세션 반환, 없으면 신규 세션 반환 
		HttpSession session = request.getSession();
		
		// 세션에 로그인 회원 정보 보관 
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		return "redirect:"+redirectURL;
	}
	
	
	
	/*
	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		expireCookie(response, "memberId");
		return "redirect:/";
	}

	@PostMapping("/logout")
	public String logoutV2(HttpServletRequest request) {
		sessionManager.expire(request);
		return "redirect:/";
	}*/
	
	@PostMapping("/logout")
	public String logoutV3(HttpServletRequest request) {
		// 기존 세션을 들고온다.
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			// 세션을 제거한다.
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	private void expireCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName,null);
		// 해당 쿠키를 즉시 종료한다.
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	
}
