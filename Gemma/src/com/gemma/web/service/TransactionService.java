package com.gemma.web.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftl.derived.FetalLexer;
import com.ftl.derived.FetalParser;
import com.ftl.derived.FetalParser.TransactionContext;
import com.ftl.helper.Transaction;
import com.ftl.helper.Variable;
import com.gemma.web.beans.BeansHelper;
import com.gemma.web.beans.FileLocations;
import com.gemma.web.exceptions.BailErrorStrategy;
import com.gemma.web.exceptions.FetalExceptions;

@Service("transactionServiceService")
public class TransactionService extends Transaction {
	private static Logger logger = Logger.getLogger(TransactionService.class.getName());
	
	@Autowired
	private AccountingService accountingService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	final String filePath = "C:/Users/Timothy Marcoe/Fetal Archive/git/Fetal Workbench/src/com/ftl/transactions/";
	
	@Override
	public void credit(Double amount, String account) {

		String message = String.format("credit(P%.02f, %s)", amount, account);
		logger.debug(message);
		accountingService.credit(account, amount);
	}
	

	@Override	
	public void debit(Double amount, String account) {
		String message = String.format("debit(P%.02f, %s)", amount, account);
		logger.debug(message);
		accountingService.debit(account, amount);
	}

	@Override
	public void ledger(char type, Double amount, String account, String description) {
		
		String message = String.format("ledger(%c, P%.02f, %s, %s)", type, amount, account, description);
		logger.debug(message);
		accountingService.ledger(type, amount, account, description);
	}

	@Override
	public void beginTrans() {
		logger.info("******************************Begin Transaction******************************");
		//session().beginTransaction();
	}
	
	@Override
	public void commitTrans() {
		logger.info("******************************Commit Transaction******************************");
		//session().getTransaction().commit();
	}
	
	@Override
	public void printVarList() {
		logger.info("\n*********************Defined Variables*********************");
		List<Variable> varList = getVarList();
		for(Variable var: varList) {
			logger.info(String.format("%s %s = %s\n", var.getType(), var.getName(), var.getValue()));
		}
	}
	
	@SuppressWarnings("unused")
	@Override
	public void loadRule(String rule) throws IOException, RecognitionException, RuntimeException {
		
		BeansHelper bean = new BeansHelper();
		FileLocations fi = (FileLocations) bean.getBean("file-context.xml", "fileLocations");

		File file = new File(fi.getTransactionPath() + rule);
		Reader read = null;
		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ANTLRInputStream in = new ANTLRInputStream(read);        
		FetalLexer lexer = new FetalLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FetalParser parser = new FetalParser(tokens);
        
        parser.removeErrorListeners(); // remove ConsoleErrorListener
        parser.addErrorListener(new FetalExceptions()); // add ours
        parser.setErrorHandler(new BailErrorStrategy());
       	TransactionContext context = parser.transaction(this);
	}

}
