package com.gemma.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.web.dao.Returns;
import com.gemma.web.dao.ReturnsDao;

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
	


}
