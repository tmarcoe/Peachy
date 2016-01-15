package com.gemma.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.ChartOfAccounts;
import com.gemma.spring.web.dao.ChartOfAccountsContainer;
import com.gemma.spring.web.dao.ChartOfAccountsDao;

@Service("chartOfAccountsService")
public class ChartOfAccountsService {
	
	@Autowired
	private ChartOfAccountsDao chartOfAccountsDao;
	
	public List<ChartOfAccounts> listAccounts() {
		return chartOfAccountsDao.listAccounts();
	}

	public void create(ChartOfAccounts chartOfAccounts) {
		chartOfAccountsDao.create(chartOfAccounts);
	}

	public void update(List<ChartOfAccounts> accountsList) {
		
		chartOfAccountsDao.update(accountsList);
		
	}
	
	public void update(ChartOfAccounts chartOfAccounts) {
		chartOfAccountsDao.update(chartOfAccounts);
	}

	public boolean delete(String deleteKey) {
		return chartOfAccountsDao.delete(deleteKey);
	}
	
	public ChartOfAccountsContainer getContainer() {
		ChartOfAccountsContainer container = new ChartOfAccountsContainer();
		
		container.setAccountsList(listAccounts());
		
		return container;
	}

	public void save(ChartOfAccounts charOfaccounts) {
		chartOfAccountsDao.save(charOfaccounts);
	}

	public ChartOfAccounts getAccount(String detailKey) {
		return chartOfAccountsDao.getAccount(detailKey);
	}

}
