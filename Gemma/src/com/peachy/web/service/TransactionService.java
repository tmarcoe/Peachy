package com.peachy.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import com.ftl.derived.FetalLexer;
import com.ftl.derived.FetalParser;
import com.ftl.derived.FetalParser.BlockContext;
import com.ftl.derived.FetalParser.TransactionContext;
import com.ftl.helper.SalesHeader;
import com.ftl.helper.SalesItem;
import com.ftl.helper.Transaction;
import com.ftl.helper.Variable;
import com.peachy.web.beans.FileLocations;
import com.peachy.web.beans.Order;
import com.peachy.web.dao.Coupons;
import com.peachy.web.dao.Inventory;
import com.peachy.web.dao.InvoiceHeader;
import com.peachy.web.dao.InvoiceItem;
import com.peachy.web.dao.Returns;
import com.peachy.web.dao.UsedCoupons;
import com.peachy.web.exceptions.BailErrorStrategy;
import com.peachy.web.exceptions.FetalExceptions;

@Service("transactionServiceService")
public class TransactionService extends Transaction {
	private static Logger logger = Logger.getLogger(TransactionService.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private FileLocations fileLocations;

	@Autowired
	private ChartOfAccountsService chartOfAccountsService;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceHeaderService invoiceHeaderService;

	@Autowired
	InventoryService inventoryService;

	@Autowired
	UsedCouponsService usedCouponsService;

	@Autowired
	GeneralLedgerService generalLedgerService;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void importSales(List<InvoiceItem> invList) {
		double grandTotal = 0;
		int invNum = 0;
		long totalItems = 0;
		for (InvoiceItem item : invList) {
			SalesItem sales = new SalesItem();
			invNum = item.getInvoiceNum();
			sales.setPrice(item.getPrice());
			sales.setQty(item.getAmount());
			sales.setTax(item.getTax());
			grandTotal += (item.getPrice() * item.getAmount());
			totalItems += item.getAmount();
			super.items.put(item.getSkuNum(), sales);
		}
		SalesHeader hdr = new SalesHeader();
		hdr.setInvoiceNum(invNum);
		hdr.setTotalItems(totalItems);
		hdr.setTotalPrice(grandTotal);
	}

	@Override
	public void credit(Double amount, String account) {

		String message = String.format("credit(P%.02f, %s)", amount, account);
		logger.debug(message);
		chartOfAccountsService.creditAccount(account, amount);
	}

	@Override
	public void debit(Double amount, String account) {
		String message = String.format("debit(P%.02f, %s)", amount, account);

		logger.debug(message);
		chartOfAccountsService.debitAccount(account, amount);
	}

	@Override
	public void ledger(char type, Double amount, String account,
			String description) {

		String message = String.format("ledger(%c, P%.02f, %s, %s)", type,
				amount, account, description);
		logger.debug(message);
		generalLedgerService.ledger(type, amount, account, description);
	}

	@Override
	public void beginTrans() {
		logger.info("******************************Begin Transaction******************************");
		// session().beginTransaction();
	}

	@Override
	public void commitTrans() {
		logger.info("******************************Commit Transaction******************************");
		// session().getTransaction().commit();
	}

	@Override
	public void printVarList() {
		logger.info("\n*********************Defined Variables*********************");
		List<Variable> varList = getVarList();
		for (Variable var : varList) {
			logger.info(String.format("%s %s = %s\n", var.getType(),
					var.getName(), var.getValue()));
		}
	}

	@Override
	public double getBalance(String account) {

		return chartOfAccountsService.getBalance(account);
	}

	@SuppressWarnings("unused")
	@Override
	public void loadRule(String rule) throws IOException, RecognitionException,
			RuntimeException {

		URL url = new URL(fileLocations.getTransactionPath() + rule);
		BufferedReader read = new BufferedReader(new InputStreamReader(
				url.openStream()));

		ANTLRInputStream in = new ANTLRInputStream(read);
		FetalLexer lexer = new FetalLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FetalParser parser = new FetalParser(tokens);

		parser.removeErrorListeners(); // remove ConsoleErrorListener
		parser.addErrorListener(new FetalExceptions()); // add ours
		parser.setErrorHandler(new BailErrorStrategy());
		TransactionContext context = parser.transaction(this);
	}

	@SuppressWarnings("unused")
	@Override
	public void loadCoupon(String cpn) throws IOException {
		URL url = new URL(fileLocations.getCouponPath() + cpn);

		BufferedReader read = new BufferedReader(new InputStreamReader(
				url.openStream()));
		ANTLRInputStream in = new ANTLRInputStream(read);
		FetalLexer lexer = new FetalLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FetalParser parser = new FetalParser(tokens);

		parser.removeErrorListeners(); // remove ConsoleErrorListener
		parser.addErrorListener(new FetalExceptions()); // add ours
		parser.setErrorHandler(new BailErrorStrategy());
		TransactionContext context = parser.transaction(this);

	}

	public void processSales(InvoiceHeader header) throws RecognitionException,
			IOException, RuntimeException {

		setAmount(header.getTotal());
		setAddedCharges(header.getAddedCharges());
		setShipCharges(header.getShippingCost());
		setTax(header.getTotalTax());
		setDescription("Internet Sales");

		loadRule("purchase.trans");

	}

	public void processShoppingCart(InvoiceHeader header)
			throws RecognitionException, IOException, RuntimeException {

		header.setProcessed(new Date());
		List<InvoiceItem> itemList = invoiceService.getInvoice(header);

		processSales(header);
		for (InvoiceItem item : itemList) {
			inventoryService.commitInventory(item);
		}


		invoiceHeaderService.updateHeader(header);

	}

	public void podProcessShoppingCart(InvoiceHeader header)
			throws RecognitionException, IOException, RuntimeException {
		List<InvoiceItem> itemList = invoiceService.getInvoice(header);

		header.setProcessed(new Date());
		podPurchase(header);
		for (InvoiceItem item : itemList) {
			inventoryService.depleteInventory(item);
		}

		invoiceHeaderService.updateHeader(header);

	}

	public void podPurchase(InvoiceHeader header) throws RecognitionException,
			IOException, RuntimeException {

		setAmount(header.getTotal());
		setAddedCharges(header.getAddedCharges());
		setShipCharges(header.getShippingCost());
		setTax(header.getTotalTax());
		setDescription("Internet Sales");

		loadRule("podpurchase.trans");

	}

	public void processPod(InvoiceHeader header) throws RecognitionException,
			IOException, RuntimeException {

		setAmount(header.getTotal());
		setTax(header.getTotalTax());
		setAddedCharges(header.getAddedCharges());
		setShipCharges(header.getShippingCost());
		setDescription("Update POD Sale upon notification of product delivery.");

		loadRule("podcomplete.trans");
	}

	@Transactional
	public void useCoupon(InvoiceHeader header, Coupons coupon)
			throws IOException, RecognitionException, NestedServletException {
		List<InvoiceItem> inv = invoiceService.getInvoice(header);
		if (header.getProcessed() == null) {
			//Only purge if the invoice has not been billed
			invoiceService.purgeCoupons(inv, header.getUserID());
		}
		setHistory(usedCouponsService.getCount(header.getUserID(),
				coupon.getCouponID()));
		importSales(inv);
		addVariable("couponValue", "double", 0.0);
		addVariable("couponTax", "double", 0.0);
		setFilePath(fileLocations.getCouponPath());
		loadCoupon(coupon.getRuleName());

		UsedCoupons used = new UsedCoupons();
		used.setCouponID(coupon.getCouponID());
		used.setUserID(header.getUserID());
		InvoiceItem invItem = new InvoiceItem();

		invItem.setAmount(1);
		invItem.setInvoiceNum(header.getInvoiceNum());
		Double val = (double) getValue("couponValue");
		Double tax = (double) getValue("couponTax");
		Float cv = Float.valueOf(val.toString());
		Float tx = Float.valueOf(tax.toString());
		invItem.setPrice(cv);
		if (invItem.getPrice() < 0) {
			invItem.setProductName(coupon.getDescription());
			invItem.setSkuNum(coupon.getCouponID());
			invItem.setDescription("");
			invItem.setImage("");
			invItem.setTax(tx);
			invItem.setWeight(0);
			invItem.setAmtInStock(0);

			invoiceService.addLineItem(invItem);
			usedCouponsService.create(used);
		}
	}

	@Override
	public Object getValue(String name) {
		Object obj = super.getValue(name);

		return obj;
	}

	public void returnMerchandise(Returns returns) throws RecognitionException,
			IOException, RuntimeException, URISyntaxException {
		if ("D".equalsIgnoreCase(returns.getDecision())) {
			InvoiceItem item = invoiceService.getInvoiceItem(
					returns.getInvoiceNum(), returns.getSkuNum());
			item.setAmount(item.getAmount() + returns.getAmtReturned());
			invoiceService.updateItem(item);
		} else if ("A".equals(returns.getDecision())) {
			setAmount(returns.getPurchasePrice());
			setTax(returns.getPurchaseTax());
			setDescription("Return of Merchandise");
			loadRule("returns.trans");
			if (returns.isReturnToStock() == true) {
				inventoryService.stockInventory(returns.getSkuNum(),
						returns.getAmtReturned());
			}
		}
	}

	public void purchaseInventory(Order order) throws RecognitionException,
			IOException, RuntimeException {
		logger.info("Begin Purchase");
		setAmount(order.getPrice());
		setTax(order.getTax());
		setDescription("Purchase of inventory (SKU #"
				+ order.getInventory().getSkuNum() + ")");
		loadRule("inventory.trans");

		Inventory inventory = order.getInventory();
		inventory.setAmtInStock(inventory.getAmtInStock() + order.getAmount());
		inventoryService.update(inventory);
		logger.info("End Purchase");
	}

	@SuppressWarnings("unused")
	@Override
	public void loadBlock(String rule) throws RecognitionException, IOException {
		URL url = new URL(getFilePath() + rule);
		BufferedReader read = new BufferedReader(new InputStreamReader(
				url.openStream()));

		ANTLRInputStream in = new ANTLRInputStream(read);
		FetalLexer lexer = new FetalLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FetalParser parser = new FetalParser(tokens);

		BlockContext context = parser.block(this);

	}

}
