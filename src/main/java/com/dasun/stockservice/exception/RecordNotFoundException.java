package com.dasun.stockservice.exception;

public class RecordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7937111560754857495L;

	public RecordNotFoundException(String error) {
		super(error);
	}
}
