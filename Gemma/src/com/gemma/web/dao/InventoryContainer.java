package com.gemma.web.dao;

import java.util.List;

public class InventoryContainer implements ListContainer{
	private List<Inventory> inventoryList;

	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
	}

	@Override
	public List<?> getList() {
		return inventoryList;
	}
	
	
}
