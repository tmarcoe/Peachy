package com.peachy.web.exceptions;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.apache.log4j.Logger;

public class FetalExceptions extends BaseErrorListener {
	private static Logger logger = Logger.getLogger(FetalExceptions.class);
	
	@Override
	public void syntaxError(Recognizer <?,?> recognizer, 
			Object offendingSymbol, 
			int line, int charPositionInLine, 
			String msg, RecognitionException e) {
		msg = "Error @" + line + "," + charPositionInLine + ": " + msg;
		logger.error(msg);
		
	}
	
	
}
