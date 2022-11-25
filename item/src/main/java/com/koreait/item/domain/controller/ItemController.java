package com.koreait.item.domain.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// /basic/items/아이템의ID
	@GetMapping("/{itemId}") // 변수로 경로 받아오기
	public String item(@PathVariable long itemId, Model model) { // 어노테이션 후 파라미터는 선언해준 변수에 맞춰주기
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	
	
//	------------------ add ---------------------
	
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
//	@PostMapping("/add")
	public String save( @RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		model.addAttribute("item",item);
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String saveV2( @ModelAttribute("item")Item item) {
		
		// @ModerlAttribute 가 해주는 역할
//			@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity,	Model model) {
		
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
		
		itemRepository.save(item);
//		model.addAttribute("item",item);
		return "basic/item";
	}
	
	
	/*
	 *  @ModelAttribute 에서 name 생략 
	 *  -> 생략시 model에 저장되는 name은 클래스명의 첫 글자만 소문자로 등록
	 *  	Item -> item
	 */
//	@PostMapping("/add")
	public String saveV3( @ModelAttribute Item item) {					
		itemRepository.save(item);		
		return "basic/item";
	}
	
	/*
	 *  @ModelAttribute 자체도 생략 가능, 그러나 가독성을 위해 권장하지 않음
	 */
//	@PostMapping("/add")
	public String saveV4( Item item) {					
		itemRepository.save(item);		
		return "basic/item";
	}
	
	// redirect 방식으로 넘겨주어야 새로고침등 불필요한 행동시 추가 생성 되는 것을 막을 수 있다.
//	@PostMapping("/add")
	public String saveV5( Item item) {					
		itemRepository.save(item);		
		return "redirect:/basic/items/" + item.getId();
	}
	
	/*
	 * redirect:/basic/items/{itemId}
	 * 	-> @PathVariable		: {itemId}
	 * 	-> 나머지는 파라미터로 처리	: ?status=true
	 * 	즉 매핑시켜서 적어준 값들은 경로로 들어가고 그외 직접 적어주지 않은 값들은 파라미터로 들어간다. 
	 */
	@PostMapping("/add")
	public String saveV6( Item item, RedirectAttributes redirectAttributes ) {					
		Item saveitem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveitem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/basic/items/{itemId}";
	}
	
//	------------------ add ---------------------
	
	@GetMapping("/{itemId}/edit")
	public String editForm( @PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		// 상세페이지로 이동
		return "redirect:/basic/items/{itemId}";
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
