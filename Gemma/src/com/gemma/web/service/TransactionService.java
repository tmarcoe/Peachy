package com.gemma.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftl.helper.Transaction;

@Service("transactionServiceService")
public class TransactionService extends Transaction {
		
	@Autowired
	private AccountingService accountingService;
	
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

}
