package com.dasun.stockservice.exception;

public class InputValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 808038417252041333L;
	
	private final String parameterName;
	
	public InputValidationException(String error, String code) {
		super(error);
		this.parameterName = code;
	}

	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}
}
