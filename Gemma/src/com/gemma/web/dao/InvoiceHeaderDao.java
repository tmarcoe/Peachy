package com.gemma.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Component("InvoiceHeaderDao")
public class InvoiceHeaderDao {
	
	@Autowired
	InvoiceItemDao invoiceItemDao;
	
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
	public PagedListHolder<InvoiceHeader> getProcessedInvoices() {
		String hql = "from InvoiceHeader where processed != null and dateShipped = null";
		
		return new PagedListHolder<InvoiceHeader>(session().createQuery(hql).list());
	}

	public InvoiceHeader totalHeader(InvoiceHeader header) {
		List<InvoiceItem> itemList = invoiceItemDao.getInvoice(header);
		
		double total = 0;
		double totalTax = 0;

		for(InvoiceItem item: itemList) {
			total += (item.getAmount() * item.getPrice());
			totalTax += (item.getAmount() * item.getTax());

			header.setTotal(total);
			header.setTotalTax(totalTax);
		}
		
		return header;
	}

	@SuppressWarnings("unchecked")
	public PagedListHolder<InvoiceHeader> getHistory(int userID) {
		String hql = "from InvoiceHeader where userID = :userID";
		
		return new PagedListHolder<InvoiceHeader>(session().createQuery(hql).setInteger("userID", userID).list());

	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getProcessedInvoicesList() {
		String hql = "from InvoiceHeader where processed != null and dateShipped = null";
		
		return session().createQuery(hql).list();
	}

}
