package com.gemma.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.gemma.web.dao.Coupons;
import com.gemma.web.dao.CouponsDao;

@Service("couponsService")
public class CouponsService {
	
	@Autowired
	CouponsDao couponsDao;
	
	public void create(Coupons coupons) {
		couponsDao.create(coupons);
	}

	public void update(Coupons coupons) {
		couponsDao.update(coupons);
	}
	
	public void delete(Coupons coupons) {
		couponsDao.delete(coupons);
	}
	
	public Coupons retrieve(String couponNumber) {
		return couponsDao.retrieve(couponNumber);
	}

	public PagedListHolder<Coupons> getList() {
		return new PagedListHolder<Coupons>(couponsDao.getList());
	}
}
