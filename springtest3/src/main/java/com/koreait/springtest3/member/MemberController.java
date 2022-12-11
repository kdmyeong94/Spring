package com.koreait.springtest3.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MemberController {


	@ModelAttribute("members")
	public List<Member> save(){
		List<Member> member = new ArrayList<Member>();
		
		member.add(new Member(1, 1, "memberA", "01012345670"));
		member.add(new Member(2, 2, "memberB", "01012345671"));
		member.add(new Member(3, 1, "memberC", "01012345672"));
		member.add(new Member(4, 1, "memberD", "01012345673"));
		member.add(new Member(5, 2, "memberE", "01012345674"));
		member.add(new Member(6, 2, "memberF", "01012345675"));

		return member;
	}
	
	@GetMapping("member")
	public String members() {
		return "member";
	}
}
