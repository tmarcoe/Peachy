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
	private InventoryDao inventoryDao;
	
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

	@Transactional
	public void deleteInvoiceItem(int invoiceNum, String skuNum) {
		String hqlDeleteItem = "delete from InvoiceItem where invoiceNum = :invoiceNum and skuNum = :skuNum";
		String hqlExistsItem = "from InvoiceItem where invoiceNum = :invoiceNum";
		String hqlDeleteHeader = "delete from InvoiceHeader where invoiceNum = :invoiceNum";
		session().createSQLQuery(hqlDeleteItem).setInteger("invoiceNum", invoiceNum)
											.setString("skuNum", skuNum)
											.executeUpdate();
		@SuppressWarnings("unchecked")
		List<InvoiceHeader> qry = session().createQuery(hqlExistsItem).setInteger("invoiceNum", invoiceNum).list();
		if (qry.size() == 0) {
			session().createQuery(hqlDeleteHeader).setInteger("invoiceNum", invoiceNum).executeUpdate();
		}
	}


	public InvoiceItem getInvoiceItem(int invoiceNum, String skuNum) {
		String hql = "from InvoiceItem where invoiceNum = :invoiceNum and skuNum = :skuNum";
		
		return (InvoiceItem) session().createQuery(hql)
									  .setInteger("invoiceNum", invoiceNum)
									  .setString("skuNum", skuNum)
									  .uniqueResult();
	}


	public void updateItem(InvoiceItem item) {
		session().update(item);
	}

	public double totalShoppingCart(InvoiceHeader header) {

		double total = 0;
		double tax = 0;
		
		List<InvoiceItem> invoiceItems = getInvoice(header);
		for(InvoiceItem item: invoiceItems) {
			Inventory product = inventoryDao.getItem(item.getSkuNum());
			total += item.getPrice() * item.getAmount();
			tax += product.getTaxAmt() * item.getAmount();

		}
		return total + tax;
	}

}
