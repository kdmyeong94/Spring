package com.study.self.self;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/basic")
public class Controller {

	@GetMapping("text-basic")
	public String text(Model model) {
		model.addAttribute("data", "hello");
		model.addAttribute("udata", "<hr>hello");
		return "basic/text-basic";
	}
	
}
