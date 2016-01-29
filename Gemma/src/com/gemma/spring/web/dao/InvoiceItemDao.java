package com.gemma.spring.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Component("InvoiceItemDao")
public class InvoiceItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}


	public InvoiceItem addLineItem(InvoiceItem item) {
		String hql = "from InvoiceItem where invoiceNum = :invoiceNum and skuNum = :skuNum";

		InvoiceItem oldItem = (InvoiceItem) session().createQuery(hql)
													 .setInteger("invoiceNum", item.getInvoiceNum())
													 .setString("skuNum", item.getSkuNum())
													 .uniqueResult();
		if (oldItem == null) {
			session().save(item);
		}else{
			String hqlUpdate = "update InvoiceItem set amount = :amount where invoiceNum = :invoiceNum and skuNum = :skuNum";
			item.setAmount(oldItem.getAmount() + item.getAmount());
			
			session().createQuery(hqlUpdate).setInteger("amount", item.getAmount())
											.setInteger("invoiceNum", item.getInvoiceNum())
											.setString("skuNum",item.getSkuNum())
											.executeUpdate();
		}
		
		return item;
	}


	@SuppressWarnings("unchecked")
	public List<InvoiceItem> getInvoice(InvoiceHeader header) {
		String hql ="from InvoiceItem where invoiceNum = :invoiceNum";
		
		return (List<InvoiceItem>) session().createQuery(hql).setInteger("invoiceNum", header.getInvoiceNum()).list();

	}

}
