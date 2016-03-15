package com.gemma.web.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
	private double amount = 0.00;
	private double tax = 0.00;
	private String Description = "";
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public List<String> getVariables() {
		List<String> varList = new ArrayList<String>();
        for(String key: variables.keySet()) {
           varList.add(key);
        }
        return varList;
	}
	
	public void addVariable(String name, Object var ) {
		
		variables.put(name, var);
	}
	
}
