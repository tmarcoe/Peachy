package com.gemma.web.ftl_helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.gemma.web.service.ChartOfAccountsService;
import com.gemma.web.service.GeneralLedgerService;

public class Transaction {
	private double amount = 0.00;
	private double tax = 0.00;
	private String Description = "";
	private Map<String, Variable> variables = new HashMap<String, Variable>();
	
	@Autowired
	GeneralLedgerService generalLedgerService;
	
	@Autowired
	ChartOfAccountsService chartOfAccountsService;
	
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
	
	public void assignVariable(String name, Object obj){
		Variable var = variables.get(name);
		if (var != null) {
			var.setValue(obj);
			variables.replace(name, var);
		}
	}
	
	public void addVariable(String name, String type, Object value ) {
		Variable var = new Variable();

		var.setType(type);
		var.setValue(value);
		var.setName(name);
		
		variables.put(name, var);
	}
	
	public void addVariable(String name, String type) {
		Variable var = new Variable();

		var.setType(type);
		var.setName(name);
		
		variables.put(name, var);
	}
	
	public String getType(String name) {
		return variables.get(name).getType();
	}
	public Object getValue(String name) {
		Variable var = variables.get(name);
		
		if (var != null) {
		return	var.getValue();
		}
		
		return null;
	}
	
	public List<Variable> getVarList() {
		List<Variable> varList = new ArrayList<Variable>();
		
		for(Entry<String, Variable> entry: variables.entrySet()) {
			varList.add(entry.getValue());
		}
		
		return varList;
	}
	
	public boolean isVariable(String name) {
		return (variables.get(name) != null);
	}
	
	public void credit(Double amount, String account) {
		System.out.printf("credit( %.02f, %s)\n",amount, account);
	}
	
	public void debit(Double amount, String account) {
		System.out.printf("debit( %.02f, %s)\n",amount, account);
	}

	public void ledger(char type, Double amount, String account, String description) {
		System.out.printf("ledger(%c, %.02f, %s, %s)\n", type, amount, account, description);
	}

}
