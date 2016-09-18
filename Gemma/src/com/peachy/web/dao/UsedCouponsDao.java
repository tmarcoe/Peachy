package com.peachy.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usedCouponsDao")
public class UsedCouponsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(UsedCoupons usedCoupons) {
		session().save(usedCoupons);
		session().disconnect();
		
	}

	public void update(UsedCoupons usedCoupons) {
		session().update(usedCoupons);
		session().disconnect();
		
	}

	public void delete(UsedCoupons usedCoupons) {
		session().delete(usedCoupons);
		session().disconnect();
		
	}

	public long getCount(int userID, String couponID) {
		String hql = "SELECT count(*) FROM UsedCoupons WHERE userID = :userID and couponID = :couponID";

		Long history = (Long) session().createQuery(hql)
				.setInteger("userID", userID).setString("couponID", couponID)
				.uniqueResult();
		session().disconnect();

		return history;
	}

	@SuppressWarnings("unchecked")
	public UsedCoupons retrieve(String skuNum, int userID) {
		String hql = "FROM UsedCoupons WHERE userID = :userID and couponID = :couponID";
		List<UsedCoupons> result = session().createQuery(hql).setString("couponID", skuNum).setInteger("userID", userID).list();
		UsedCoupons uc = result.get(0);
		session().disconnect();
		
		return uc;
	}

}
