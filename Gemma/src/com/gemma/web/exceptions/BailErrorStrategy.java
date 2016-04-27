package com.gemma.web.exceptions;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

import com.ftl.derived.FetalParser;
import com.ftl.helper.UserDefinedException;

public class BailErrorStrategy extends DefaultErrorStrategy {

	/**
	 * Instead of recovering from exception e, rethrow it wrapped * in a generic
	 * RuntimeException so it is not caught by the * rule function catches.
	 * Exception e is the "cause" of the * RuntimeException.
	 */
	@Override
	public void recover(Parser recognizer, RecognitionException e) {
		//throw new RuntimeException(e);
	}

	/**
	 * Make sure we don't attempt to recover inline; if the parser *
	 * successfully recovers, it won't throw an exception.
	 */

	/** Make sure we don't attempt to recover from problems in subrules. */
	@Override
	public void sync(Parser recognizer) {}

	@Override
	public Token recoverInline(Parser recognizer) throws RuntimeException {
		
		((FetalParser) recognizer).endStatement();
		return ((FetalParser) recognizer).endStatement().stop;
		
	}
}