package com.peachy.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("inventoryDao")
public class InventoryDao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public void create(Inventory inventory) {
		session().save(inventory);
		session().disconnect();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listProducts() {
		Criteria crit = session().createCriteria(Inventory.class);
		List<Inventory > inv = crit.list();
		session().disconnect();
		
		return inv;
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listProducts(String category) {
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("category", category));
		List <Inventory> inv = crit.list();
		session().disconnect();

		return inv;
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listProducts(String category, String subCategory) {
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("category", category));
		crit.add(Restrictions.eq("subCategory", subCategory));
		List<Inventory> inv = crit.list();
		session().disconnect();

		return inv;
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listSaleItems(){
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("onSale", true));
		List<Inventory> inv = crit.list();
		session().disconnect();

		return inv;
	}
	
	public void saveOrUpdate(List<Inventory> inventory){
		for(Inventory inv: inventory) {
			session().saveOrUpdate(inv);
		}
		session().disconnect();

	}
	
	public void update(Inventory inventory) {
		session().update(inventory);
		session().disconnect();

	}
	
	public boolean delete(String skuNum) {
		Query query = session().createQuery("delete from Inventory where skunum=:skuNum");
		query.setString("skuNum", skuNum);
		int count = query.executeUpdate();
		session().disconnect();

		return ( count == 1);
	}

	public Inventory getItem(String skuNum) {
		
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("skuNum", skuNum));
		Inventory inv = (Inventory) crit.uniqueResult();
		session().disconnect();

		return inv;
	}

	public void depleteInventory(InvoiceItem item) {
		String hql = "update Inventory set amtCommitted = amtCommitted - :amount where skuNum = :skuNum";
		session().createQuery(hql).setInteger("amount", item.getAmount()).setString("skuNum", item.getSkuNum()).executeUpdate();
		session().disconnect();

	}

	@SuppressWarnings("unchecked")
	public List<String> getCategory() {
		String hql = "select DISTINCT category FROM Inventory";
		List<String> cat = session().createQuery(hql).list();
		session().disconnect();

		return cat;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSubCategory(String category) {
		String hql = "select DISTINCT subCategory FROM Inventory where category = :category";
		List<String> subCat = session().createSQLQuery(hql).setString("category", category).list();
		session().disconnect();

		return subCat;
	}

	@SuppressWarnings("unchecked")
	public List<Inventory> getReplenishList() {
		String hql = "from Inventory where amtInStock < minQuantity";
		List<Inventory> inv = session().createQuery(hql).list();
		session().disconnect();

		return inv;
	}

	public void commitInventory(InvoiceItem item) {
		String hql = "update Inventory set amtInStock = amtInStock - :amount, amtCommitted = amtCommitted + :amount where skuNum = :skuNum";	
		session().createQuery(hql).setInteger("amount", item.getAmount()).setString("skuNum", item.getSkuNum()).executeUpdate();
		session().disconnect();

	}
}
