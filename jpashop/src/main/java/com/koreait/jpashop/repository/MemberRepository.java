package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	// 기존에 JpaMain 등에서 사용했던 긴 문장들은 @RequiredArgsConstructor 와
	// private final EntityManager em; 로 모두 대체 가능하다.
	
	// @Autowired : spring boot lib 사용 시 @Autowired를 지원한다.
	@Autowired
	private final EntityManager em;
	
	// 저장
	public void save(Member member) {
		em.persist(member);
	}
	
	// 1건 조회
	public Member findOne( Long id) {
		return em.find(Member.class, id);
	}
	
	// 여러건 조회
	public List<Member> findAll(){
		return em.createQuery("select m from Member m ", Member.class).getResultList();
	}
	
	// 이름으로 조회
	public List<Member> findName(String name){
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name).getResultList();
	}
	
}
