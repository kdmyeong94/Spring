package com.koreait.thymeleaf.bean;

import org.springframework.stereotype.Component;

//@Component가 있으면 스프링 컨테이너 박스 안에 helloBean이라는 객체가 생성됨
@Component("helloBean") 
public class HelloBean {
	
	public String hello(String data) {
		return "Hello" + data;
	}
}
