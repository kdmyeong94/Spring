package com.koreait.springtestquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("param")
public class RequestController {

	@GetMapping("home")
	public String home() {
		return "param/home";
	}
	
	@GetMapping("get")
	public String get(@RequestParam("answer")int param, Model model ) {
		
		if(param == 300) {
			model.addAttribute("answer", param + "은(는) 정답입니다.");
		} else {
			model.addAttribute("answer", param + "은(는) 오답입니다.");
		}
		
		return "param/getresult";
	}
	
	@PostMapping("post")
	public String post(@RequestParam(value = "user_name", required = false, defaultValue = "guest")String name,
			@RequestParam(value= "user_age", required= false, defaultValue = "-1")int age, Model model) {
		model.addAttribute("user", name + "님은 " + age + "입니다");
//		model.addAttribute("user_name", name); // 어떤 방법이 더 효율적인지는 모르겠어서
//		model.addAttribute("user_age", age); // 두 방법 모두 가능하기에 써보았습니다.
		return "param/postresult";
	}
}
