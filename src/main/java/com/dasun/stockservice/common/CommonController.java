package com.dasun.stockservice.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommonController {

	/**
	 * This method is used to generate common response for stock service
	 * 
	 * @param output
	 * @param status
	 * @return ResponseEntity
	 */
	protected ResponseEntity<?> generateResponse(Object output, String listSize, HttpStatus status) {
		return new ResponseEntity<>(output, setHeaders(listSize), status);
	}

	protected ResponseEntity<?> generateResponse(Object output, HttpStatus status) {
		return new ResponseEntity<>(output, setHeaders(output), status);
	}

	/**
	 * This method is used to set X-Total-Count header and other common headers
	 * 
	 * @param output
	 * @return HttpHeaders
	 */
	private HttpHeaders setHeaders(String listSize) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Expose-Headers", "X-Total-Count");
		if (listSize != "") {
			responseHeaders.set("X-Total-Count", listSize + "");
		}

		return responseHeaders;
	}

	@SuppressWarnings("rawtypes")
	private HttpHeaders setHeaders(Object output) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Expose-Headers", "X-Total-Count");
		if (output instanceof List) {
			responseHeaders.set("X-Total-Count", ((List) output).size() + "");
		}

		return responseHeaders;
	}

}
