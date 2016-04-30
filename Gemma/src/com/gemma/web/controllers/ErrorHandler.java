package com.gemma.web.controllers;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import com.gemma.web.service.AccountingService;

@ControllerAdvice
public class ErrorHandler {
	private static Logger logger = Logger.getLogger(AccountingService.class.getName());
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		ex.printStackTrace();
		logger.error("DataAccessException");
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException ex) {
		logger.error("AccessDeniedException");
		return "blocked";
	}
	
	@ExceptionHandler(RuntimeErrorException.class)
	public String handleRuntimeException(RuntimeErrorException ex) {
		logger.error("RuntimeErrorException");
		return "error";
	}
	
	@ExceptionHandler(NestedServletException.class)
	public String handleNestedServletException(NestedServletException ex) {
		logger.error("NestedServletException");
		return "error";
	}
	
	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex) {
		logger.error("IOException");
		return "error";
	}
}
