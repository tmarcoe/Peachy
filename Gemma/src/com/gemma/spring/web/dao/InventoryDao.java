package com.gemma.spring.web.dao;

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
		crit.add(Restrictions.eq("subcategory", subCategory));
		
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
}
