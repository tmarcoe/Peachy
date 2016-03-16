package com.gemma.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.web.dao.InventoryDao;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceHeaderDao;
import com.gemma.web.dao.InvoiceItem;
import com.gemma.web.dao.InvoiceItemDao;

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
		
		double total = 0;
		double totalTax = 0;
		for(InvoiceItem item: itemList) {
			total += (item.getAmount() * item.getPrice());
			totalTax += (item.getAmount() * item.getTax());
			depleteInventory(item);
			header.setTotal(total);
			header.setTotalTax(totalTax);
		}
		invoiceHeaderDao.updateHeader(header);

		accountingService.processSales(header);
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


	public double totalShoppingCart(InvoiceHeader header) {
		return invoiceItemDao.totalShoppingCart(header);
	}

}
