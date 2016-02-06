package com.gemma.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.ChartOfAccounts;
import com.gemma.spring.web.dao.GeneralLedger;

@Service("accountingService")
public class AccountingService {
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private ChartOfAccountsService chartOfAccountsService;

	
	
	
	public void transferFunds(GeneralLedger ledger, ChartOfAccounts from, ChartOfAccounts to, double amount ) {
		chartOfAccountsService.debitAccount(from, amount);
		ledger.setAccountNum(from.getAccountNum());
		ledger.setDebitAmt((float) amount);
		generalLedgerService.addEntry(ledger);
		chartOfAccountsService.creditAccount(to, amount);
		ledger.setAccountNum(to.getAccountNum());
		ledger.setDebitAmt(0);
		ledger.setCreditAmt((float) amount);
		generalLedgerService.addEntry(ledger);
	}




}
