/**
 * 
 */
package com.gemma.web.dao;

import java.util.List;

/**
 * @author Timothy Marcoe
 *
 */
public class ChartOfAccountsContainer implements ListContainer{
	private List<ChartOfAccounts> accountsList;

	public List<ChartOfAccounts> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<ChartOfAccounts> accountsList) {
		this.accountsList = accountsList;
	}

	@Override
	public List<?> getList() {
		
		return accountsList;
	}

}
