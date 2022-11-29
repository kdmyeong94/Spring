package com.koreait.item.enumeration;

public enum Type {
	// static final String WALKING = "워킹화"; x4 번의 작업을 아래와 같이 생성 
	
	WALKING("워킹화"),
	RUNNING("러닝화"),
	TRACKING("트래킹화"),
	HIKING("등산화");
	
	final private String name;
	
	private Type(String name) { // enum에서 생성자와 같은 개념
		this.name = name;
	}
	
	public String getName() { // 문자를 받아오는 함수
		return name;
	}
	
}
