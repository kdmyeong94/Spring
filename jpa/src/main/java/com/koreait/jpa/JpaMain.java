package com.koreait.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
			
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transcation 발생
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			// JPQL
			// JPA는 쿼리를 짤 때 Table을 대상으로 쿼리를 작성하지 않고,
			// member 객체를 통해서 쿼리를 작성한다.
//			List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
//													
//			for(Member member : result) {
//				System.out.println("member.name = " + member.getName());
//			}
			
			// 녹화본 보기
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
					.setFirstResult(5)		// 5번부터
					.setMaxResults(10)		// 10개 가지고와
					.getResultList();

			for(Member member : result) {
			System.out.println("member.name = " + member.getName());
			}
			
//			//회원 조회
//			Member findMember = em.find(Member.class, 3L);
//			System.out.println("====================================");
//			System.out.println("findMember.id : " + findMember.getId());
//			System.out.println("findMember.name : " + findMember.getName());
//			
//			// 회원 수정
//			findMember.setName("UpdateUser");
//			
//			// 회원 삭제		
//			em.remove(findMember);
			
			
			//회원 등록
//			Member member = new Member();
//			
//			// 데이터 추가 ( 확인용 )
//			member.setId(1L);
//			member.setName("userA");
//			
//			// persist : DB에 저장
//			em.persist(member);
			
			
//			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();		
		}
		
	}

}
