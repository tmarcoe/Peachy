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

import com.peachy.web.orm.Coupons;

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
		crit.add(Restrictions.eq("name", couponNumber));
		
		return (Coupons) crit.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Coupons> getList() {
		return session().createQuery("from Coupons").list();
	}
}
