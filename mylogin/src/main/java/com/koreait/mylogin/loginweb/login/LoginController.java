package com.koreait.mylogin.loginweb.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.mylogin.loginweb.member.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm) {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes) {
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		
		System.out.println(loginMember);
		
		if( loginMember == null) {
			// 로그인실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}
		
		/*
		 *  - addAttribute : url 뒤에 붙는다.
		 *  - addFlashAttribute : url 뒤에 붙지 않는다.
		 */
		
		// 로그인 성공
		redirectAttributes.addFlashAttribute("msg","로그인 성공");
		return "redirect:/";
	}
	
}
