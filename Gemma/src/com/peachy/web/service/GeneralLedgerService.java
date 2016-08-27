package com.peachy.web.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.peachy.web.beans.DatePicker;
import com.peachy.web.dao.GeneralLedger;
import com.peachy.web.dao.GeneralLedgerDao;

@Service("generalLedgerService")
public class GeneralLedgerService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GeneralLedgerDao generalLedgerDao;
	
	public void addEntry(GeneralLedger ledger) {
		generalLedgerDao.addEntry(ledger);
	}
	
	public void ledger(char type, double amount, String account, String desc) {
		Float debitAmt = (float) 0;
		Float creditAmt = (float) 0;
		
		switch (type) {
		case 'C':
			creditAmt = (float) amount;
			break;
			
		case 'D':
			debitAmt = (float) amount;
			break;
		}
		
		GeneralLedger ledger = new GeneralLedger(account, 0, new Date(), desc, debitAmt, creditAmt);
		addEntry(ledger);
	}

	public List<GeneralLedger> getList() {
		return generalLedgerDao.getList();
	}

	public List<GeneralLedger> getList(DatePicker picker) {
		return generalLedgerDao.getList(picker);
	}
	
	public PagedListHolder<GeneralLedger> getPagedList(DatePicker picker) {
		return new PagedListHolder<GeneralLedger>(getList(picker));
	}
	
}
