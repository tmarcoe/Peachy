package com.gemma.web.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import com.braintreegateway.exceptions.UnexpectedException;
import com.gemma.web.service.AccountingService;

@ControllerAdvice
public class ErrorHandler {
	private static Logger logger = Logger.getLogger(AccountingService.class.getName());
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		ex.printStackTrace();
		logger.error("DataAccessException: " + ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException ex) {
		logger.error("AccessDeniedException: " + ex.getMessage());
		return "blocked";
	}
	
	@ExceptionHandler(RuntimeErrorException.class)
	public String handleRuntimeException(RuntimeErrorException ex) {
		logger.error("RuntimeErrorException: " + ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(NestedServletException.class)
	public String handleNestedServletException(NestedServletException ex) {
		logger.error("NestedServletException: " + ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex) {
		logger.error("IOException: " + ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(MessagingException.class)
	public String handleMessagingException(MessagingException e){
		logger.error("MessagingException: " + e.getMessage());
		return "error";
	}
	
	@ExceptionHandler(SecurityException.class) 
	public String handleSecurityException(SecurityException e){
		logger.error("SecurityException: " + e.getMessage());
		return "error";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e) {
		logger.error("IllegalArgumentException: " + e.getMessage());
		return "error";
	}
	@ExceptionHandler(UnknownHostException.class)
	public String handleUnknownHostException(UnknownHostException e){
		logger.error("UnknownHostException: " + e.getMessage());
		return "error";
	}
	@ExceptionHandler(URISyntaxException.class)
	public String handleURISyntaxException(URISyntaxException e) {
		logger.error("URISyntaxException: " + e.getMessage());
		return "error";
	}
	@ExceptionHandler(UnexpectedException.class)
	public String handleUnexpectedException(UnexpectedException e) {
		logger.error("UnexpectedException: " + e.getMessage());
		return "nointernet";
	}
}
