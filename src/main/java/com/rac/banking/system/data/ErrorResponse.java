package com.rac.banking.system.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error Response.
 */
public class ErrorResponse{

	/** Error Message */
	@JsonProperty("message")
	private String message;
	
	/** HTTP Status Code */
	@JsonProperty("code")
	private int code;

	/**
	 * Constructs a new ErrorResponse object with the specified message and code.
	 *
	 * @param message the error message
	 * @param code the error code
	 */
	public ErrorResponse(String message, int code) {
	    super();
	    this.message = message;
	    this.code = code;
	}

	/**
	 * Returns the error message associated with this ErrorResponse object.
	 *
	 * @return the error message
	 */
	public String getMessage() {
	    return message;
	}

	/**
	 * Sets the error message for this ErrorResponse object.
	 *
	 * @param message the error message to set
	 */
	public void setMessage(String message) {
	    this.message = message;
	}

	/**
	 * Returns the error code associated with this ErrorResponse object.
	 *
	 * @return the error code
	 */
	public int getCode() {
	    return code;
	}

	/**
	 * Sets the error code for this ErrorResponse object.
	 *
	 * @param code the error code to set
	 */
	public void setCode(int code) {
	    this.code = code;
	}

}