package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import daoInterface.MemberEjbInt;
import model.Loan;
import model.Member;



@Stateless
public class MemberEJB implements MemberEjbInt {
	
	@Inject
	EntityManager em;
	
	
	@Override
	public void createMember(Member member) {
		em.persist(member);
	}



	@Override
	public List<Member> listMembers() {
		
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
			   List<Member> results = query.getResultList();
			   return results;
	}

	@Override
	public List<Member> searchByLastName(String lastName) {
		return (List<Member>) em.find(Member.class, lastName);
	}

//	@Override
//	public List<Member> searchByLastName(String lastName) {
//		
//		  TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.lastName= :replace", Member.class);
//		  		query.setParameter("replace", lastName);
//			   List<Member> member = query.getResultList();
//			   return member;
//		
//	}
	
	@Override
	public Member findById(Long memberid) {
		return em.find(Member.class, memberid);
	}

	
	@Override
	public Member updateById(Long memberId) {
		 TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.memberId= :update", Member.class);
	  		query.setParameter("update",memberId );
		  Member member = query.getSingleResult();
		return member;
		
		
		
	}



	
}



