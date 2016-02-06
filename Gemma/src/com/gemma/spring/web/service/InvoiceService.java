package com.gemma.spring.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.ChartOfAccounts;
import com.gemma.spring.web.dao.GeneralLedger;
import com.gemma.spring.web.dao.InventoryDao;
import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.spring.web.dao.InvoiceHeaderDao;
import com.gemma.spring.web.dao.InvoiceItem;
import com.gemma.spring.web.dao.InvoiceItemDao;

@Service("invoiceService")
public class InvoiceService {
	
	@Autowired
	private InvoiceHeaderDao invoiceHeaderDao;
	
	@Autowired
	private InvoiceItemDao invoiceItemDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private AccountingService accountingService;
	
	@Autowired
	ChartOfAccountsService chartOfAccountsService;
	
	public List<InvoiceItem> getInvoice(InvoiceHeader header) {
	
		return invoiceItemDao.getInvoice(header);
	}


	public InvoiceHeader createHeader(InvoiceHeader header) {
		return invoiceHeaderDao.createHeader(header);
	}

	public InvoiceItem addLineItem(InvoiceItem item) {
		
		return invoiceItemDao.addLineItem(item);
	}

	public InvoiceHeader getOpenOrder(int userID) {
		
		return invoiceHeaderDao.getOpenOrder(userID);
	}


	public void deleteInvoiceItem(int invoiceNum, String skuNum) {
		invoiceItemDao.deleteInvoiceItem(invoiceNum, skuNum);
	}


	public InvoiceHeader getInvoiceHeader(int invoiceNum) {
		return invoiceHeaderDao.getInvoiceHeader(invoiceNum);
	}

	public InvoiceItem getInvoiceItem(int invoiceNum, String skuNum) {
		return invoiceItemDao.getInvoiceItem(invoiceNum, skuNum);
	}


	public void updateItem(InvoiceItem item) {
		invoiceItemDao.updateItem(item);
		
	}


	public void processShoppingCart(InvoiceHeader header) {
		header.setProcessed(new Date());
		List<InvoiceItem> itemList = invoiceItemDao.getInvoice(header);
		
		float total = 0;
		for(InvoiceItem item: itemList) {
			total += (item.getAmount() * item.getPrice());
			depleteInventory(item);
			header.setTotal(total);
		}
		invoiceHeaderDao.updateHeader(header);
		GeneralLedger ledger = new GeneralLedger();
		ledger.setUserID(header.getUserID());
		ledger.setDescription("Sales from the website");
		ChartOfAccounts from = chartOfAccountsService.getAccount("1000");
		ChartOfAccounts to = chartOfAccountsService.getAccount("1004");
		accountingService.transferFunds(ledger, from, to, header.getTotal());
	}

	private void depleteInventory(InvoiceItem item) {
		inventoryDao.depleteInventory(item);
	}


	public List<InvoiceHeader> getProcessedInvoices() {

		return invoiceHeaderDao.getProcessedInvoices();
	}

	public void updateHeader(InvoiceHeader header) {
		invoiceHeaderDao.updateHeader(header);
	}

}
