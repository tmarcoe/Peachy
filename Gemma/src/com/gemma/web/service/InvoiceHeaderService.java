package com.gemma.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


	public InvoiceHeader getInvoiceHeader(int invoiceNum) {
		return invoiceHeaderDao.getInvoiceHeader(invoiceNum);
	}

	public void processShoppingCart(InvoiceHeader header) {
		invoiceHeaderDao.processShoppingCart(header);
	}

	public List<InvoiceHeader> getProcessedInvoices() {

		return invoiceHeaderDao.getProcessedInvoices();
	}

	public void updateHeader(InvoiceHeader header) {
		invoiceHeaderDao.updateHeader(header);
	}

	
	public List<InvoiceHeader> getAllInvoiceHeaders() {
		return invoiceHeaderDao.getAllInvoiceHeaders();
	}




	


}
