package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@GetMapping("hello")
	public String hello(Model model) {
		System.out.println("controller 도착");
		model.addAttribute("data", "hello!!!!");
		return "hello";
	}
	
	//http://localhost:9090/hello-mvc?name=SpringMVC
	/*
	 * @RequestParam
	 * 	
	 * */
//	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String param, Model model) {
		model.addAttribute("name", param);
		return "hello-template";
	}
	
	
	/*
	 *  - required : 파라미터 값 필수 여부, true -> 필수 ( default ) - false - 필수 아님
	 * */
	@GetMapping("hello-mvc")
	public String helloMvc2(@RequestParam(value="name", required = false) String param, Model model) {
		model.addAttribute("name", param);
		return "hello-template";
	}
	
	/*  
	 * - defaultValue : 파라미터 값이 없을 경우 기본으로 들어갈 값
	 * */
//	@GetMapping("hello-mvc")
	public String helloMvc3(@RequestParam(value="name", required = false, defaultValue = "required test") String param, Model model) {
		model.addAttribute("name", param);
		return "hello-template";
	}
	
}
