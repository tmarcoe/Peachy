package com.gemma.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.spring.web.dao.ChartOfAccounts;
import com.gemma.spring.web.dao.GeneralLedger;
import com.gemma.spring.web.dao.Inventory;
import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.web.beans.Order;

@Service("accountingService")
public class AccountingService {
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private ChartOfAccountsService chartOfAccountsService;

	@Autowired 
	InventoryService inventoryService;
	
	
	
	public void transferFunds(GeneralLedger ledger, ChartOfAccounts from, ChartOfAccounts to, double amount ) {
		chartOfAccountsService.debitAccount(from, amount);
		ledger.setAccountNum(from.getAccountNum());
		ledger.setDebitAmt((float) amount);
		generalLedgerService.addEntry(ledger);
		chartOfAccountsService.creditAccount(to, amount);
		ledger.setAccountNum(to.getAccountNum());
		ledger.setDebitAmt(0);
		ledger.setCreditAmt((float) amount);
		generalLedgerService.addEntry(ledger);
	}
	
	public void processSales(GeneralLedger ledger, InvoiceHeader header) {
		ChartOfAccounts cash = chartOfAccountsService.getAccount("1000");
		double eleanor = (header.getTotal() * .02);
		chartOfAccountsService.debitAccount(cash, (header.getTotal() + header.getTotalTax()));
		ledger.setAccountNum(cash.getAccountNum());
		ledger.setDebitAmt((float) (header.getTotal() + header.getTotalTax()));
		ledger.setDescription("From Internet Sales");
		generalLedgerService.addEntry(ledger);
		ledger.setDebitAmt(0);
		ChartOfAccounts sales = chartOfAccountsService.getAccount("1009");
		chartOfAccountsService.creditAccount(sales, (header.getTotal() - eleanor));
		ledger.setAccountNum(sales.getAccountNum());
		ledger.setCreditAmt((float) (header.getTotal() - eleanor));
		ledger.setDescription("Net Sales amount minus agreed upon payment");
		generalLedgerService.addEntry(ledger);
		ChartOfAccounts tax = chartOfAccountsService.getAccount("1010");
		chartOfAccountsService.creditAccount(tax, header.getTotalTax());
		ledger.setAccountNum(tax.getAccountNum());
		ledger.setCreditAmt((float) header.getTotalTax());
		ledger.setDescription("Tax from internet sales.");
		generalLedgerService.addEntry(ledger);
		ChartOfAccounts ate = chartOfAccountsService.getAccount("1011");
		chartOfAccountsService.creditAccount(ate, eleanor);
		ledger.setAccountNum(ate.getAccountNum());
		ledger.setCreditAmt((float) eleanor);
		ledger.setDescription("Agreed upon per sale price for creating the website.");
		generalLedgerService.addEntry(ledger);
	}
	public void purchaseInventory(Order order) {
		GeneralLedger ledger = new GeneralLedger();
		ChartOfAccounts cash = chartOfAccountsService.getAccount("1000");
		chartOfAccountsService.creditAccount(cash, order.getPrice() + order.getTax());
		ledger.setAccountNum(cash.getAccountNum());
		ledger.setCreditAmt((float) (order.getPrice() + order.getTax()));
		ledger.setDescription("Purchase of inventory (SKU #" + order.getInventory().getSkuNum() +")");
		generalLedgerService.addEntry(ledger);
		ledger.setCreditAmt(0);
		ChartOfAccounts purchase = chartOfAccountsService.getAccount("1003");
		chartOfAccountsService.debitAccount(purchase, order.getPrice() + order.getTax());
		ledger.setAccountNum(purchase.getAccountNum());
		ledger.setDebitAmt((float) (order.getPrice() + order.getTax()));
		ledger.setDescription("Purchase of inventory (SKU #" + order.getInventory().getSkuNum() +")");
		generalLedgerService.addEntry(ledger);
		Inventory inventory = order.getInventory();
		inventory.setAmtInStock(inventory.getAmtInStock() + order.getAmount());
		inventoryService.update(inventory);
	}


}
