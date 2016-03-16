package com.gemma.web.ftl_helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.gemma.web.ftl.FtlLexer;
import com.gemma.web.ftl.FtlParser;
import com.gemma.web.ftl.FtlParser.TransactionContext;


public class Fetal {
	
	public void start(Transaction transaction, String fileName) {
		final String filePath = "/Gemma/src/com/gemma/web/transactions/";

		File file = new File(filePath + fileName);
		Reader read = null;
		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			loadRule(read, transaction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public void loadRule(Reader read, Transaction transaction) throws IOException {

		ANTLRInputStream in = new ANTLRInputStream(read);        
		FtlLexer lexer = new FtlLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FtlParser parser = new FtlParser(tokens);
        TransactionContext context = parser.transaction(transaction);
        ParseTreeWalker walker = new ParseTreeWalker();
        FollowRule rule = new FollowRule();
        walker.walk(rule, context);
	}
	

}
