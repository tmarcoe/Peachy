package com.gemma.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.web.dao.InventoryDao;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceItem;
import com.gemma.web.dao.InvoiceItemDao;

@Service("invoiceService")
public class InvoiceService {
	
	@Autowired
	private InvoiceItemDao invoiceItemDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	ChartOfAccountsService chartOfAccountsService;
	
	public List<InvoiceItem> getInvoice(InvoiceHeader header) {
	
		return invoiceItemDao.getInvoice(header);
	}


	public InvoiceItem addLineItem(InvoiceItem item) {
		
		return invoiceItemDao.addLineItem(item);
	}

	public void deleteInvoiceItem(int invoiceNum, String skuNum) {
		invoiceItemDao.deleteInvoiceItem(invoiceNum, skuNum);
	}

	public InvoiceItem getInvoiceItem(int invoiceNum, String skuNum) {
		return invoiceItemDao.getInvoiceItem(invoiceNum, skuNum);
	}


	public void updateItem(InvoiceItem item) {
		invoiceItemDao.updateItem(item);
		
	}


	public void depleteInventory(InvoiceItem item) {
		inventoryDao.depleteInventory(item);
	}


	public double totalShoppingCart(InvoiceHeader header) {
		return invoiceItemDao.totalShoppingCart(header);
	}


	public void deleteInvoice(InvoiceHeader header) {
		invoiceItemDao.deleteInvoice(header);
	}


	public boolean hasCoupons(int invoiceNum) {
		return invoiceItemDao.hasCoupons(invoiceNum);
	}


	public InvoiceItem getCouponFromInvoice(Integer invoiceNum) {
		return invoiceItemDao.getCouponFromInvoice(invoiceNum);
	}


}
