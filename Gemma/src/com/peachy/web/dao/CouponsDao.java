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
@Component("couponsDao")
public class CouponsDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(Coupons coupons) {
		session().save(coupons);
		session().disconnect();
				
	}
	
	public void update(Coupons coupons) {
		session().update(coupons);
		session().disconnect();
	}
	
	public void delete(Coupons coupons){
		session().delete(coupons);
		session().disconnect();
	}
	public Coupons retrieve(String couponNumber) {
		Criteria crit = session().createCriteria(Coupons.class);
		crit.add(Restrictions.eq("name", couponNumber));
		Coupons coupons = (Coupons) crit.uniqueResult();
		session().disconnect();
		
		return coupons;
	}
	@SuppressWarnings("unchecked")
	public List<Coupons> getList() {
		List<Coupons> couponsList = session().createQuery("from Coupons").list();
		session().disconnect();
		
		return couponsList;
	}
}
