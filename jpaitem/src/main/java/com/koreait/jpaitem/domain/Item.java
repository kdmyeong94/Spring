package com.koreait.jpaitem.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Item {
	
	@Id @GeneratedValue // 전략을 특정해주지 않으면 자동으로 생성 시퀀스를 생성한다.
	@Column(name = "ITEM_ID")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;
	
	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItem = new ArrayList<OrderItem>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setItem(this);
		this.orderItem.add(orderItem);
	}
	
	
}
