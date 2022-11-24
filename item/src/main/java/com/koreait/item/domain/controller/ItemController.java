package com.koreait.item.domain.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.item.domain.item.Item;
import com.koreait.item.domain.item.ItemRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor 
// @RequiredArgsConstructor : lombok에서 제공되는 기능, final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다.
public class ItemController {
	
	private final ItemRepository itemRepository;
	
//	@Autowired
	// 생성자가 1개만 있으면 @Autowired 생략가능
//	public ItemController(ItemRepository itemRepository) {   //@RequiredArgsConstructor가 생성자를 대신 만들어주기때문에 생략가능
//		this.itemRepository = itemRepository;
//	}
	
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	// 테스트용 데이터 추가
	@PostConstruct
	public void init() {
//		System.out.println("초기화 메서드");
		itemRepository.save(new Item("teatA", 10000, 10));
		itemRepository.save(new Item("teatB", 20000, 20));
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("종료 메서드");
	}
	
	
}
