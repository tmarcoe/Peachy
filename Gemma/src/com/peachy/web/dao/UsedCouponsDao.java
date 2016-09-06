package com.peachy.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.peachy.web.orm.UsedCoupons;

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
	}

	public void update(UsedCoupons usedCoupons) {
		session().update(usedCoupons);
	}

	public void delete(UsedCoupons usedCoupons) {
		session().delete(usedCoupons);
	}

	public long getCount(int userID, String couponID) {
		String hql = "SELECT count(*) FROM UsedCoupons WHERE userID = :userID and couponID = :couponID";

		Long history = (Long) session().createQuery(hql)
				.setInteger("userID", userID).setString("couponID", couponID)
				.uniqueResult();

		return history;
	}

	@SuppressWarnings("unchecked")
	public UsedCoupons retrieve(String skuNum, int userID) {
		String hql = "FROM UsedCoupons WHERE userID = :userID and couponID = :couponID";
		List<UsedCoupons> result = session().createQuery(hql).setString("couponID", skuNum).setInteger("userID", userID).list();
		return result.get(0);
	}

}
