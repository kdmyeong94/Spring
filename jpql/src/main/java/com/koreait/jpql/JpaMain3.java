package com.koreait.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Address;
import com.koreait.jpql.domain.Member;


public class JpaMain3 {

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

//			em.createQuery("select o.address from Order o", Address.class).getResultList();
			
			//exception
//			em.createQuery("select o.address from Address o", Address.class).getResultList();
			
			em.createQuery("select distinct m.username, m.age from Member m").getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
		
		
		
	}
	
}
