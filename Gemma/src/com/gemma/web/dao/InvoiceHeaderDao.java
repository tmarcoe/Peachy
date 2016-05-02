package com.gemma.web.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.antlr.v4.runtime.RecognitionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gemma.web.service.AccountingService;

@Transactional
@Repository
@Component("InvoiceHeaderDao")
public class InvoiceHeaderDao {
	
	@Autowired
	InvoiceItemDao invoiceItemDao;
	
	@Autowired
	AccountingService accountingService;
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public InvoiceHeader getOpenOrder(int userID) {
		String hql = "from InvoiceHeader where userID = :userID and processed = null";
		
		session().createQuery(hql).setInteger("userID", userID).uniqueResult();
		
		return (InvoiceHeader) session().createQuery(hql).setInteger("userID", userID).uniqueResult();
	}

	public InvoiceHeader createHeader(InvoiceHeader header) {
		session().save(header);
		
		return header;
	}

	public InvoiceHeader getInvoiceHeader(int invoiceNum) {
		String hql = "from InvoiceHeader where invoiceNum = :invoiceNum";
		
		
		return (InvoiceHeader) session().createQuery(hql).setInteger("invoiceNum", invoiceNum).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getAllInvoiceHeaders() {
		String hql = "from InvoiceHeader where processed = null and dateShipped = null";

		return session().createQuery(hql).list();
	}

	public void updateHeader(InvoiceHeader header) {
		session().update(header);
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getProcessedInvoices() {
		String hql = "from InvoiceHeader where processed != null and dateShipped = null";
		List<InvoiceHeader> headers = session().createQuery(hql).list();
		
		return headers;
	}

	public List<InvoiceHeader> getInvoiceHeader() {
		return null;
	}
	public void processShoppingCart(InvoiceHeader header) throws RecognitionException, IOException, RuntimeException {
		header.setProcessed(new Date());
		List<InvoiceItem> itemList = invoiceItemDao.getInvoice(header);
		
		double total = 0;
		double totalTax = 0;
		for(InvoiceItem item: itemList) {
			total += (item.getAmount() * item.getPrice());
			totalTax += (item.getAmount() * item.getTax());
			inventoryDao.depleteInventory(item);
			header.setTotal(total);
			header.setTotalTax(totalTax);
		}
		updateHeader(header);

		accountingService.processSales(header);
	}


}
