package com.peachy.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
		session().disconnect();
		
	}
	
	public void update(Returns returns) {
		session().update(returns);
		session().disconnect();

	}

	@SuppressWarnings("unchecked")
	public List<Returns> getReturnsList(String username) {
		String hql = "FROM Returns where username = :username";
		List<Returns> retList = session().createQuery(hql).setString("username", username).list();
		session().disconnect();
		
		return retList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Returns> getReturnsList() {
		String hql = "FROM Returns where dateProcessed = null";
		List<Returns> retList = session().createQuery(hql).list();
		session().disconnect();
		
		return retList;
	}
	
	public Returns getRma(Integer rmaId) {
		Criteria crit = session().createCriteria(Returns.class);
		crit.add(Restrictions.eq("rmaId", rmaId));
		Returns returns = (Returns) crit.uniqueResult();
		session().disconnect();
		
		return returns;
	}

	public void delete(Integer rmaId) {
		Returns returns = getRma(rmaId);
		session().delete(returns);
		session().disconnect();
		
	}




}
