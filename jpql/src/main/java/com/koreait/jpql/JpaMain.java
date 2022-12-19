package com.koreait.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Member;


public class JpaMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member member = new Member();
			member.setUsername("memeber1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
			
			// 업데이트가 될 경우, 영속성 컨텍스트에서 관리가 된다고 볼 수 있다.
			Member findMember = result.get(0);
			findMember.setAge(20);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
