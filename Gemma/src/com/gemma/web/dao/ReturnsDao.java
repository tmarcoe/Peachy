package com.gemma.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("returnDao")
public class ReturnsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public void create(Returns returns) {
		
		session().save(returns);
	}
	
	public void update(Returns returns) {
		session().update(returns);
	}

	public PagedListHolder<Returns> getReturnsList(String username) {
		String hql = "FROM Returns where username = :username";
		
		@SuppressWarnings("unchecked")
		PagedListHolder<Returns> returnsList = new PagedListHolder<Returns>(session().createQuery(hql)
																					 .setString("username", username)
																					 .list());
		return returnsList;
	}
	
	public PagedListHolder<Returns> getReturnsList() {
		String hql = "FROM Returns where dateProcessed = null";
		
		@SuppressWarnings("unchecked")
		PagedListHolder<Returns> returnsList = new PagedListHolder<Returns>(session().createQuery(hql).list());
		
		return returnsList;
	}
	
	public Returns getRma(Integer rmaId) {
		Criteria crit = session().createCriteria(Returns.class);
		crit.add(Restrictions.eq("rmaId", rmaId));
		
		return (Returns) crit.uniqueResult();
	}

	public void delete(Integer rmaId) {
		Returns returns = getRma(rmaId);
		
		session().delete(returns);
		
	}




}
