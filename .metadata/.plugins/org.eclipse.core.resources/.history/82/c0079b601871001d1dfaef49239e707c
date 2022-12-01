package com.koreait.mylogin.loginweb;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.koreait.mylogin.loginweb.member.Member;
import com.koreait.mylogin.loginweb.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component // spring bean 에 등록
@RequiredArgsConstructor
public class TestDataInit {

	private final MemberRepository memberRepository;
	// 테스트 데이터 추가	
	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test!");
		member.setName("테스터");
		memberRepository.save(member);		
	}
}
