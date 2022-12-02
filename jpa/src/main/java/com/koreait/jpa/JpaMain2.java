package com.koreait.jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {

	public static void main(String[] args) {
			
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transcation 발생
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// 비영속 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("Hello");
			
			// 영속상태
			// EntityManager 안에 있는 영속 컨텍스트로 관리
			// DB쿼리가 실행되는지 확인
			System.out.println("=======before=========");
			em.persist(member);
			System.out.println("=======after=========");
			
			// 쿼리가 보내지는 시점
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();		
		}
		
	}

}
