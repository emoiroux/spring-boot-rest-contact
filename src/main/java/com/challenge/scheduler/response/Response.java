package com.challenge.scheduler.response;

import org.springframework.http.HttpStatus;

/**
 * The user response.
 */

public class Response {

	// Body content
	private String message;

	// Header Http status
	private HttpStatus status;

	public static Response accepted(String message) {
		Response response = new Response();
		response.setStatus(HttpStatus.ACCEPTED);
		response.setMessage(message);
		return response;
	}

	public static Response exception(String message) {
		Response response = new Response();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage(message);
		return response;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
