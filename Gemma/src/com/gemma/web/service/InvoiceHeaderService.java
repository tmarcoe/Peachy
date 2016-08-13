package com.gemma.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceHeaderDao;

@Service("invoiceHeaderService")
public class InvoiceHeaderService {
	
	@Autowired
	private InvoiceHeaderDao invoiceHeaderDao;
	
	public InvoiceHeader createHeader(InvoiceHeader header) {
		return invoiceHeaderDao.createHeader(header);
	}

	public InvoiceHeader getOpenOrder(int userID) {
		
		return invoiceHeaderDao.getOpenOrder(userID);
	}
	public PagedListHolder<InvoiceHeader> getHistory(int userID) {
		return new PagedListHolder<InvoiceHeader>(invoiceHeaderDao.getHistory(userID));
	}

	public InvoiceHeader getInvoiceHeader(int invoiceNum) {
		return invoiceHeaderDao.getInvoiceHeader(invoiceNum);
	}

	public PagedListHolder<InvoiceHeader> getProcessedInvoices() {

		return new PagedListHolder<InvoiceHeader>(invoiceHeaderDao.getProcessedInvoices());
	}

	public void updateHeader(InvoiceHeader header) {
		invoiceHeaderDao.updateHeader(header);
	}

	
	public List<InvoiceHeader> getAllInvoiceHeaders() {
		return invoiceHeaderDao.getAllInvoiceHeaders();
	}

	public InvoiceHeader totalHeader(InvoiceHeader header) {
		return invoiceHeaderDao.totalHeader(header);
	}

	public List<InvoiceHeader> getProcessedInvoicesList() {
		
		return invoiceHeaderDao.getProcessedInvoicesList();
	}

}
