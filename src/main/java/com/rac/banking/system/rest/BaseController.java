package com.rac.banking.system.rest;

import java.util.Arrays;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rac.banking.system.data.Customer;
import com.rac.banking.system.data.ErrorResponse;
import com.rac.banking.system.service.BaseService;

import jakarta.annotation.PostConstruct;

/**
 * Abstract base controller class providing common functionality for REST controllers.
 */
public abstract class BaseController{
	
	/** Constant representing a request. */
	protected static final String REQ = "Request";
	
	/** Constant representing a response. */
	protected static final String RES = "Response";
	
	/** Logger instance for logging. */
	protected Logger log;
	
	/** Object writer for JSON serialization. */
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
	/**
	 * Set Logger
	 */
	@PostConstruct
	protected abstract void setLogger();
	
	/**
	 * Array of HTTP status codes considered as success.
	*/
	static HttpStatus[] okSts = {
			HttpStatus.OK,
			HttpStatus.CREATED,
			HttpStatus.ACCEPTED
	};
	
	/**
	 * Logs the request or response message.
	 * @param msg The message to log.
	 * @param type The type of message (Request or Response).
	*/
	protected void logReqNRes(Object msg, String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(type + ": ");
		try {
			sb.append(null != msg? this.ow.writeValueAsString(msg): "{}");
		}catch (Exception e) {
			this.log.error("Error Occurred when processing", e);
		}
		this.log.info(sb.toString());
	}
	
	/**
	 * Constructs a ResponseEntity based on the message, status, and error flag.
	 * @param msg The message to include in the response body.
	 * @param sts The HTTP status code.
	 * @param isErr Flag indicating if it's an error response.
	 * @return The constructed ResponseEntity.
	*/
	protected ResponseEntity<Object> getRes (Object msg, HttpStatus sts, boolean isErr) {
		if(msg instanceof String && !Arrays.asList(okSts).contains(sts) && isErr) {
			ErrorResponse errRes = new ErrorResponse((String) msg, sts.value());
			this.log.info("Error Message: "+msg);
			this.logReqNRes(errRes, RES);
			return ResponseEntity.status(sts).body(errRes);
		} 
		this.logReqNRes(msg, RES);
		return ResponseEntity.status(sts).body(msg);
	}
}
