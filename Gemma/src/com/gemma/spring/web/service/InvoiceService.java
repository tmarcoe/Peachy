package com.gemma.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.spring.web.dao.InvoiceHeaderDao;
import com.gemma.spring.web.dao.InvoiceItem;
import com.gemma.spring.web.dao.InvoiceItemDao;

@Service("invoiceService")
public class InvoiceService {
	
	@Autowired
	InvoiceHeaderDao invoiceHeaderDao;
	
	@Autowired
	InvoiceItemDao invoiceItemDao;
	
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



}
