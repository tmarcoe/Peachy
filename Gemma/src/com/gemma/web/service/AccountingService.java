package com.gemma.web.service;

import java.io.IOException;
import java.io.Serializable;

import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemma.web.beans.Order;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceItem;
import com.gemma.web.dao.InvoiceItemDao;
import com.gemma.web.dao.Returns;

@Service("accountingService")
public class AccountingService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private ChartOfAccountsService chartOfAccountsService;

	@Autowired 
	private InventoryService inventoryService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private InvoiceItemDao invoiceItemDao;
	
	private static Logger logger = Logger.getLogger(AccountingService.class.getName());
	
	public void processSales(InvoiceHeader header) throws RecognitionException, IOException, RuntimeException {

		transactionService.setAmount(header.getTotal());
		transactionService.setTax(header.getTotalTax());
		transactionService.setDescription("Internet Sales");

		transactionService.loadRule("purchase.trans");
		
	}
	
	public void purchaseInventory(Order order) throws RecognitionException, IOException, RuntimeException {
		logger.info("Begin Purchase");
		transactionService.setAmount(order.getPrice());
		transactionService.setTax(order.getTax());
		transactionService.setDescription("Purchase of inventory (SKU #" + order.getInventory().getSkuNum() +")");		
		transactionService.loadRule("inventory.trans");
		
		Inventory inventory = order.getInventory();
		inventory.setAmtInStock(inventory.getAmtInStock() + order.getAmount());
		inventoryService.update(inventory);
		logger.info("End Purchase");
	}
	
	public void returnMerchandise(Returns returns) throws RecognitionException, IOException, RuntimeException {
		if ("D".equalsIgnoreCase(returns.getDecision())) {
			InvoiceItem item = invoiceItemDao.getInvoiceItem(returns.getInvoiceNum(), returns.getSkuNum());
			item.setAmount(item.getAmount() + returns.getAmtReturned());
			invoiceItemDao.updateItem(item);
		} else if ("A".equals(returns.getDecision())) {
			transactionService.setAmount(returns.getPurchasePrice());
			transactionService.setTax(returns.getPurchaseTax());
			transactionService.setDescription("Return of Merchandise");
			transactionService.loadRule("returns.trans");
			if ( returns.isReturnToStock() == true ) {
				inventoryService.stockInventory(returns.getSkuNum(), returns.getAmtReturned());
			}
		}
	}
	
	public void ledger(char type, Double amount, String account, String description) {
		generalLedgerService.ledger(type, amount, account, description);
	}
	
	public void debit(String account, Double amount) {
		chartOfAccountsService.debitAccount(account, amount);
	}
	
	public void credit(String account, Double amount) {
		chartOfAccountsService.creditAccount(account, amount);
	}

}
