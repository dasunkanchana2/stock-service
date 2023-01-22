package com.dasun.stockservice.exception;

public class ResourceAccessValidationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4488247062305242311L;

	public ResourceAccessValidationException(String error) {
		super(error);
	}
}