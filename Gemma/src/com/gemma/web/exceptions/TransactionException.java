package com.gemma.web.exceptions;

import org.apache.log4j.Logger;

import com.ftl.helper.UserDefinedException;

public class TransactionException extends UserDefinedException {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TransactionException.class);

	public TransactionException(int line, int pos, String message) {
		super(line, pos, "");
		message = "Error @" + line + "," + pos + ": " + message;
		logger.error(message);
	}

}
