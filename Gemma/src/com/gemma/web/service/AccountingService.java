package com.gemma.web.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftl.antlr.FtlLexer;
import com.ftl.antlr.FtlParser;
import com.ftl.antlr.FtlParser.TransactionContext;
import com.gemma.web.beans.Order;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InvoiceHeader;

@Service("accountingService")
public class AccountingService {
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private ChartOfAccountsService chartOfAccountsService;

	@Autowired 
	InventoryService inventoryService;
	
	@Autowired
	TransactionService transactionService;
	
	public void processSales(InvoiceHeader header) {

		transactionService.setAmount(header.getTotal());
		transactionService.setTax(header.getTotalTax());
		transactionService.setDescription("Internet Sales");

		start("purchase.trans");
		
	}
	
	public void purchaseInventory(Order order) {
		transactionService.setAmount(order.getPrice());
		transactionService.setTax(order.getTax());
		transactionService.setDescription("Purchase of inventory (SKU #" + order.getInventory().getSkuNum() +")");		
		start("inventory.trans");
		
		Inventory inventory = order.getInventory();
		inventory.setAmtInStock(inventory.getAmtInStock() + order.getAmount());
		inventoryService.update(inventory);

	}

	public void start(String fileName) {
		final String filePath = "C:/Users/Timothy Marcoe/WebSite Archive/Gemma/src/com/gemma/web/transactions/";

		File file = new File(filePath + fileName);
		Reader read = null;
		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			loadRule(read);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	public void loadRule(Reader read ) throws IOException {

		ANTLRInputStream in = new ANTLRInputStream(read);        
		FtlLexer lexer = new FtlLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FtlParser parser = new FtlParser(tokens);
        @SuppressWarnings("unused")
		TransactionContext context = parser.transaction(transactionService);
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
