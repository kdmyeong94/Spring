package com.koreait.item.domain.item;
// 최초생성 1129 0343

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 전역변수에 대해 생성자를 만들어주는 어노테이션
public class DeliveryCode {
	
	private String code;
	private String displayName;
}
