package com.gemma.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.GeneralLedger;
import com.gemma.spring.web.dao.GeneralLedgerDao;

@Service("generalLedgerService")
public class GeneralLedgerService {
	
	@Autowired
	private GeneralLedgerDao generalLedgerDao;
	
	public void addEntry(GeneralLedger ledger) {
		generalLedgerDao.addEntry(ledger);
	
	}
}
