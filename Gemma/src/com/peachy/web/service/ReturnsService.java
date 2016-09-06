package com.peachy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.peachy.web.dao.ReturnsDao;
import com.peachy.web.orm.Returns;

@Service("returnsService")
public class ReturnsService {
	
	@Autowired
	private ReturnsDao returnsDao;
	
	public void create(Returns returns) {
		returnsDao.create(returns);
	}
	
	public void update(Returns returns) {
		returnsDao.update(returns);
	}

	public PagedListHolder<Returns> getReturnsList(String username) {

		return new PagedListHolder<Returns>(returnsDao.getReturnsList(username));
	}

	public PagedListHolder<Returns> getReturnsList() {

		return new PagedListHolder<Returns>(returnsDao.getReturnsList());
	}

	public Returns getRma(Integer rmaId) {
		return returnsDao.getRma(rmaId);
	}

	public void delete(Integer rmaId) {
		returnsDao.delete(rmaId);
	}



}
