package com.koreait.jpql;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Member;


public class JpaMain7 {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setUsername("memeber");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			String sql = "select m from Member m where m.age > (select avg(m2.age) from Member m2)";
			List<Member> resultList = em.createQuery(sql, Member.class).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
