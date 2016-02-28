package com.gemma.web.dao;

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
public class InventoryDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public void create(Inventory inventory) {
		
		session().save(inventory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listProducts() {
		Criteria crit = session().createCriteria(Inventory.class);
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listProducts(String category) {
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("category", category));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listProducts(String category, String subCategory) {
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("category", category));
		crit.add(Restrictions.eq("subCategory", subCategory));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> listSaleItems(){
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("onSale", true));
		
		return crit.list();
	}
	
	public void saveOrUpdate(List<Inventory> inventory){
		for(Inventory inv: inventory) {
			session().saveOrUpdate(inv);
		}
	}
	
	public void update(Inventory inventory) {
		session().update(inventory);
	}
	
	public boolean delete(String skuNum) {
		Query query = session().createQuery("delete from Inventory where skunum=:skuNum");
		query.setString("skuNum", skuNum);
		return (query.executeUpdate() == 1);
	}

	public Inventory getItem(String skuNum) {
		
		Criteria crit = session().createCriteria(Inventory.class);
		crit.add(Restrictions.eq("skuNum", skuNum));
		return (Inventory) crit.uniqueResult();
	}

	public InventoryContainer getContainer() {
		InventoryContainer container = new InventoryContainer();
		container.setInventoryList(listProducts());

		return container;
	}

	public void depleteInventory(InvoiceItem item) {
		String hql = "update Inventory set amtInStock = amtInStock - :amount where skuNum = :skuNum";
		session().createQuery(hql).setInteger("amount", item.getAmount()).setString("skuNum", item.getSkuNum()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<String> getCategory() {
		String hql = "select DISTINCT category FROM Inventory";
		return session().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<String> getSubCategory(String category) {
		String hql = "select DISTINCT subCategory FROM Inventory where category = :category";
		
		return session().createSQLQuery(hql).setString("category", category).list();
	}

	@SuppressWarnings("unchecked")
	public List<Inventory> getReplenishList(int min) {
		String hql = "from Inventory where amtInStock < :amtInStock";
		return session().createQuery(hql).setInteger("amtInStock", min).list();
	}
}
