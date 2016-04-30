package com.gemma.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftl.helper.Transaction;
import com.ftl.helper.Variable;

@Service("transactionServiceService")
public class TransactionService extends Transaction {
	private static Logger logger = Logger.getLogger(TransactionService.class.getName());
	
	@Autowired
	private AccountingService accountingService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void credit(Double amount, String account) {

		String message = String.format("credit(P%.02f, %s)", amount, account);
		logger.debug(message);
		accountingService.credit(account, amount);
	}
	

	@Override	
	public void debit(Double amount, String account) {
		String message = String.format("debit(P%.02f, %s)", amount, account);
		logger.debug(message);
		accountingService.debit(account, amount);
	}

	@Override
	public void ledger(char type, Double amount, String account, String description) {
		
		String message = String.format("ledger(%c, P%.02f, %s, %s)", type, amount, account, description);
		logger.debug(message);
		accountingService.ledger(type, amount, account, description);
	}

	@Override
	public void beginTrans() {
		logger.info("******************************Begin Transaction******************************");
		//session().beginTransaction();
	}
	
	@Override
	public void commitTrans() {
		logger.info("******************************Commit Transaction******************************");
		//session().getTransaction().commit();
	}
	
	@Override
	public void printVarList() {
		logger.info("\n*********************Defined Variables*********************");
		List<Variable> varList = getVarList();
		for(Variable var: varList) {
			logger.info(String.format("%s %s = %s\n", var.getType(), var.getName(), var.getValue()));
		}
	}


}
