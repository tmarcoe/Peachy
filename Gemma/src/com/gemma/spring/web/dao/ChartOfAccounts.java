/**
 * 
 */
package com.gemma.spring.web.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Timothy Marcoe
 *
 */
@Entity
public class ChartOfAccounts {
	
	@Id
	@NotBlank
	private String accountNum;
	@Size(min=5, max=255)
	private String accountName;
	private float accountBalance;
	private boolean debitAccount;
	private String Description;
	
	
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public boolean isDebitAccount() {
		return debitAccount;
	}
	public void setDebitAccount(boolean debitAccount) {
		this.debitAccount = debitAccount;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}
