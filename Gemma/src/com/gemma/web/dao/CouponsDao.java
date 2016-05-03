package com.gemma.web.dao;

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
@Component("couponsDao")
public class CouponsDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(Coupons coupons) {
		session().save(coupons);
	}
	
	public void update(Coupons coupons) {
		session().update(coupons);
	}
	
	public void delete(Coupons coupons){
		session().delete(coupons);
	}
	public Coupons retrieve(String couponNumber) {
		Criteria crit = session().createCriteria(Coupons.class);
		crit.add(Restrictions.idEq(couponNumber));
		
		return (Coupons) crit.uniqueResult();
	}

}
