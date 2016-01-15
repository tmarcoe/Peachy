/**
 * 
 */
package com.gemma.spring.web.dao;

import java.util.List;

/**
 * @author Timothy Marcoe
 *
 */
public class OrderContainer implements ListContainer{
	private List<Order> orderList;

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public List<?> getList() {
		return orderList;
	}

}
