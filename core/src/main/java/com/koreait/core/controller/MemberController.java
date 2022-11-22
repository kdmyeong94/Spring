package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.core.dto.MemberDTO;

@Controller
public class MemberController {
	
	// member getmapping이 이루어지는 메서드 생성
	// view : thymeleaf/member -> html파일까지 생성할 것
	
	@RequestMapping("member") // get 방식 post 방식 모두 받는 방식
	public String getMember(Model model) {
		MemberDTO member = new MemberDTO(1, "자바학생", "0101234567");
		model.addAttribute("member", member);
		return "thymeleaf/member";
	}
	
}
