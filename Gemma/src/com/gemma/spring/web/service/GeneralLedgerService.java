package com.gemma.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.GeneralLedger;
import com.gemma.spring.web.dao.GeneralLedgerDao;
import com.gemma.web.beans.DatePicker;

@Service("generalLedgerService")
public class GeneralLedgerService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GeneralLedgerDao generalLedgerDao;
	
	public void addEntry(GeneralLedger ledger) {
		generalLedgerDao.addEntry(ledger);
	
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
