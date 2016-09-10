package com.peachy.web.service;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.peachy.web.beans.Categories;
import com.peachy.web.dao.Inventory;
import com.peachy.web.dao.InventoryDao;
import com.peachy.web.dao.InvoiceItem;
import com.peachy.web.local.CurrencyExchange;

@Service("inventoryService")
public class InventoryService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	public List<Inventory> listProducts() throws ClientProtocolException, IOException, URISyntaxException {
		return inventoryDao.listProducts();
	}
	
	public List<Inventory> listProducts(String category) throws ClientProtocolException, IOException, URISyntaxException {
		return inventoryDao.listProducts(category);
	}
	
	public List<Inventory> listProducts(String category, String subCategory) throws ClientProtocolException, IOException, URISyntaxException {
		return inventoryDao.listProducts(category, subCategory);
	}
	
	public List<Inventory> listSaleItems() throws ClientProtocolException, IOException, URISyntaxException {
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
	
	public Inventory getItem(String skuNum) throws ClientProtocolException, IOException, URISyntaxException {
		Inventory item = inventoryDao.getItem(skuNum);
		return item;
	}
	
	public Inventory getItem(String skuNum, String target) throws ClientProtocolException, IOException, URISyntaxException {
		CurrencyExchange currency = new CurrencyExchange();
		Inventory item = inventoryDao.getItem(skuNum);
		item.setSalePrice((float) currency.convert(item.getSalePrice(), target));
		item.setDiscountPrice((float) currency.convert(item.getDiscountPrice(),target));
		return item;
	}
	
	public List<String> getCategory() {
		return inventoryDao.getCategory();
	}

	public List<String> getSubCategory(String category) {
		return inventoryDao.getSubCategory(category);
	}

	public PagedListHolder<Inventory> getReplenishList() {
		return new PagedListHolder<Inventory>(inventoryDao.getReplenishList());
	}
	
	public PagedListHolder<Inventory> getPagedList(Categories categories) throws ClientProtocolException, IOException, URISyntaxException {
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

	public PagedListHolder<Inventory> getPagedList() throws ClientProtocolException, IOException, URISyntaxException {
		return new PagedListHolder<Inventory>(listProducts());
	}

	public void stockInventory(String skuNum, Integer amtReturned) throws ClientProtocolException, IOException, URISyntaxException {
		Inventory inventory = getItem(skuNum);
		inventory.setAmtInStock(inventory.getAmtInStock() + amtReturned);
		update(inventory);
	}

	@SuppressWarnings("unused")
	private List<Inventory> getLocalPrices(List<Inventory> items, String target) throws ClientProtocolException, IOException, URISyntaxException {
		CurrencyExchange currency = new CurrencyExchange();
		for(Inventory item: items) {
			item.setSalePrice((float) currency.convert(item.getSalePrice(), target));
			item.setDiscountPrice((float) currency.convert(item.getDiscountPrice(), target));
		}
		return items;
	}

	public void commitInventory(InvoiceItem item) {
		inventoryDao.commitInventory(item);
	}
	
	public void depleteInventory(InvoiceItem item) {
		inventoryDao.depleteInventory(item);
	}

	
}
