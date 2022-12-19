package com.koreait.item.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	// static 사용
	private static final Map<Long, Item> store = new HashMap<Long, Item>();
	private static long sequence = 0L;
	
	// 저장
	public Item save(Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item); // item DTO에 id 넣기
		return item; // 필요없으면 사용하지 않아도 된다.
	}
	
	// id로 찾기
	public Item findById(Long id) {
		return store.get(id);
	}
	
	// 전체 찾기
	public List<Item> findAll(){
		return new ArrayList<Item>(store.values());
	}
	
	// 수정
	public void update(Long itemId, Item updateParam) {
		// item을 먼저 찾는다.
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
		
		
		// 1129 0124------------------------------------------
		findItem.setOpen(updateParam.getOpen());				// 판매여부
		
		// 1129 0124------------------------------------------
		
		// 1129 0251--------
		findItem.setRegions(updateParam.getRegions()); 			// 등록 지역
		// 1129 0251--------
		
		// 1129 0340-----
		findItem.setItemType(updateParam.getItemType());		// 상품 종류
		// 1129 0340-----
		
		//1129 0415-----
		findItem.setDeliveryCode(updateParam.getDeliveryCode());// 배송 방식
		//1129 0415-----
	}
	
	
}
