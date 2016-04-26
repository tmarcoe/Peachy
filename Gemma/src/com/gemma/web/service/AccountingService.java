package com.gemma.web.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftl.derived.FetalLexer;
import com.ftl.derived.FetalParser;
import com.ftl.derived.FetalParser.TransactionContext;
import com.ftl.helper.BailErrorStrategy;
import com.ftl.helper.UserDefinedException;
import com.gemma.web.beans.Order;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceItem;
import com.gemma.web.dao.InvoiceItemDao;
import com.gemma.web.dao.Returns;
import com.gemma.web.exceptions.FetalExceptions;

@Service("accountingService")
public class AccountingService {
	
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
	
	public void returnMerchandise(Returns returns) {
		if ("D".equalsIgnoreCase(returns.getDecision())) {
			InvoiceItem item = invoiceItemDao.getInvoiceItem(returns.getInvoiceNum(), returns.getSkuNum());
			item.setAmount(item.getAmount() + returns.getAmtReturned());
			invoiceItemDao.updateItem(item);
		} else if ("A".equals(returns.getDecision())) {
			transactionService.setAmount(returns.getPurchasePrice());
			transactionService.setTax(returns.getPurchaseTax());
			transactionService.setDescription("Return of Merchandise");
			start("returns.trans");
			if ( returns.isReturnToStock() == true ) {
				inventoryService.stockInventory(returns.getSkuNum(), returns.getAmtReturned());
			}
		}
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
		FetalLexer lexer = new FetalLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FetalParser parser = new FetalParser(tokens);
        parser.removeErrorListeners(); // remove ConsoleErrorListener
        parser.addErrorListener(new FetalExceptions()); // add ours
        parser.setErrorHandler(new BailErrorStrategy());
       try {
		@SuppressWarnings("unused")
			TransactionContext context = parser.transaction(transactionService);
       } catch (RecognitionException | UserDefinedException e) {
			transactionService.commitTrans();
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
