package com.gemma.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.gemma.web.beans.DatePicker;
import com.gemma.web.dao.GeneralLedger;
import com.gemma.web.dao.GeneralLedgerDao;

@Service("generalLedgerService")
public class GeneralLedgerService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GeneralLedgerDao generalLedgerDao;
	
	public void addEntry(GeneralLedger ledger) {
		generalLedgerDao.addEntry(ledger);
	
	}
	
	public void ledger(char type, double amount, String account, String desc) {
		GeneralLedger ledger = new GeneralLedger();
		
		switch (type) {
		case 'C':
			ledger.setCreditAmt((float) amount);
			ledger.setDebitAmt(0);
			break;
			
		case 'D':
			ledger.setDebitAmt((float) amount);
			ledger.setCreditAmt(0);
			break;
		}
		ledger.setAccountNum(account);
		ledger.setDescription(desc);
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
