package com.peachy.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

		InvoiceHeader invHdr = (InvoiceHeader) session().createQuery(hql)
				.setInteger("userID", userID).uniqueResult();
		session().disconnect();

		return invHdr;
	}

	public InvoiceHeader createHeader(InvoiceHeader header) {
		session().save(header);
		session().disconnect();

		return header;
	}

	public InvoiceHeader getInvoiceHeader(int invoiceNum) {
		String hql = "from InvoiceHeader where invoiceNum = :invoiceNum";
		InvoiceHeader invHdr = (InvoiceHeader) session().createQuery(hql)
				.setInteger("invoiceNum", invoiceNum).uniqueResult();
		session().disconnect();		

		return invHdr;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getAllInvoiceHeaders() {
		String hql = "from InvoiceHeader where processed = null and dateShipped = null";
		List<InvoiceHeader> HdrList = session().createQuery(hql).list();
		session().disconnect();
		
		return HdrList;
	}

	public void updateHeader(InvoiceHeader header) {
		session().update(header);
		session().disconnect();

	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getProcessedInvoices() {
		String hql = "from InvoiceHeader where processed != null and dateShipped = null";
		List<InvoiceHeader> hdrList = session().createQuery(hql).list();
		session().disconnect();
		
		return hdrList;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getHistory(int userID) {
		String hql = "from InvoiceHeader where userID = :userID";
		List<InvoiceHeader> hdrList = session().createQuery(hql).setInteger("userID", userID).list();
		session().disconnect();
		
		return hdrList;

	}

	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> getProcessedInvoicesList() {
		String hql = "from InvoiceHeader where processed != null and dateShipped = null";
		List<InvoiceHeader> hdrList = session().createQuery(hql).list();
		session().disconnect();
		
		return hdrList;
	}

}
