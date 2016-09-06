package com.peachy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peachy.web.dao.UsedCouponsDao;
import com.peachy.web.orm.UsedCoupons;

@Service("usedCouponsService")
public class UsedCouponsService {
	
	@Autowired
	UsedCouponsDao usedCouponsDao;

	public void create(UsedCoupons usedCoupons) {
		usedCouponsDao.create(usedCoupons);
	}
	
	public void update(UsedCoupons usedCoupons) {
		usedCouponsDao.update(usedCoupons);
	}
	
	public void delete(UsedCoupons usedCoupons){
		usedCouponsDao.delete(usedCoupons);
	}
	
	public long getCount(int userID, String couponID) {

		return usedCouponsDao.getCount(userID, couponID);
	}

	public UsedCoupons retrieve(String skuNum, int userID) {
		
		return usedCouponsDao.retrieve(skuNum, userID);
	}


	

	

}
