package com.dasun.stockservice.exception;

public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 808038417252041333L;
	
	public InvalidInputException(String error) {
		super(error);
	}

}
