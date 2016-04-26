package com.gemma.web.controllers;

import javax.management.RuntimeErrorException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		ex.printStackTrace();
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException ex) {
		return "blocked";
	}
	
	@ExceptionHandler(RuntimeErrorException.class)
	public String handleRuntimeException(RuntimeErrorException ex) {
		return "error";
	}
	
	@ExceptionHandler(NestedServletException.class)
	public String handleNestedServletException(NestedServletException ex) {
		return "error";
	}
}
