package com.gemma.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.gemma.web.beans.Categories;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InventoryContainer;
import com.gemma.web.dao.InventoryDao;

@Service("inventoryService")
public class InventoryService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	public List<Inventory> listProducts() {
		return inventoryDao.listProducts();
	}
	
	public List<Inventory> listProducts(String category) {
		return inventoryDao.listProducts(category);
	}
	
	public List<Inventory> listProducts(String category, String subCategory) {
		return inventoryDao.listProducts(category, subCategory);
	}
	
	public List<Inventory> listSaleItems() {
		return inventoryDao.listSaleItems();
	}
	public void create(Inventory inventory) {
		inventoryDao.create(inventory);
	}
	
	public void saveOrUpdate(List<Inventory> inventory) {
		inventoryDao.saveOrUpdate(inventory);
	}
	
	public void update(Inventory inventory) {
		inventoryDao.update(inventory);
	}
	
	public boolean delete(String skuNum){
		return inventoryDao.delete(skuNum);
	}
	
	public Inventory getItem(String skuNum) {
		return inventoryDao.getItem(skuNum);
	}
	
	public InventoryContainer getContainer(){
		InventoryContainer container = inventoryDao.getContainer();
		
		return container;
	}
	
	public List<String> getCategory() {
		return inventoryDao.getCategory();
	}

	public List<String> getSubCategory(String category) {
		return inventoryDao.getSubCategory(category);
	}

	public List<Inventory> getReplenishList(int min) {
		return inventoryDao.getReplenishList(min);
	}
	
	public PagedListHolder<Inventory> getPagedList(Categories categories) {
		PagedListHolder<Inventory> listHolder;
		
		if (categories.getCategory().length() == 0) {
			listHolder = new PagedListHolder<Inventory>(listProducts());
		}else if (categories.getSubCategory().length() == 0) {
			listHolder = new PagedListHolder<Inventory>(listProducts(categories.getCategory()));
		}else{
			listHolder = new PagedListHolder<Inventory>(listProducts(categories.getCategory(), categories.getSubCategory()));
		}
		
		return listHolder;
	}

	public PagedListHolder<Inventory> getPagedList() {
		return new PagedListHolder<Inventory>(listProducts());
	}

	
}
