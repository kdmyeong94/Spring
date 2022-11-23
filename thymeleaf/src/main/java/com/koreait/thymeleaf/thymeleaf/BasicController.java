package com.koreait.thymeleaf.thymeleaf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.thymeleaf.data.User;

@Controller
@RequestMapping("/basic") // 기본 URL
public class BasicController {

	@GetMapping("text-basic")// ' / ' 생략 가능
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello!");
		return "basic/text-basic";
	}
	
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "<b>Hello!!</b>");
		return "basic/text-unescaped";
	}
	
	@GetMapping("variable")
	public String variable(Model model) {
		User userA = new User("UserA", 10);
		User userB = new User("UserB", 20);
		
		List<User> list = new ArrayList<User>();
		list.add(userA);
		list.add(userB);
		
		Map<String, User> map = new HashMap<String, User>();
		map.put("UserA", userA);
		map.put("UserB", userB);
		
		model.addAttribute("user", userA);
		model.addAttribute("userList", list);
		model.addAttribute("userMap", map);
		
		return "basic/variable";
	}
	
	@GetMapping("basic-objects")
	public String basicObjects(HttpSession session ) {
		session.setAttribute("sessionData", "sessionValue");
		return "basic/basic-objects";
	}
	
	@GetMapping("date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}
	
	@GetMapping("link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}
	
}