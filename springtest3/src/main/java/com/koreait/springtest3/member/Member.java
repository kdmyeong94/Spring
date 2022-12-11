package com.koreait.springtest3.member;

import lombok.Data;

@Data
public class Member {

	private int num;
	private int gender;
	private String name;
	private String phone;
	
	public Member(int num, int gender, String name, String phone) {
		super();
		this.num = num;
		this.gender = gender;
		this.name = name;
		this.phone = phone;
	}
	

	
	
}
