package com.gemma.web.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftl.helper.Transaction;

@Service("transactionServiceService")
public class TransactionService extends Transaction {
		
	@Autowired
	private AccountingService accountingService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void credit(Double amount, String account) {

		accountingService.credit(account, amount);
	}
	

	@Override	
	public void debit(Double amount, String account) {

		accountingService.debit(account, amount);
	}

	@Override
	public void ledger(char type, Double amount, String account, String description) {

		accountingService.ledger(type, amount, account, description);
	}

	@Override
	public void beginTrans() {
		session().beginTransaction();
	}
	
	@Override
	public void commitTrans() {
		session().getTransaction().commit();
	}


}
