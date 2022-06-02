package com.example.eccomerce.exception;

public class EndereçoExisteException extends Exception {

private String message;
	
	public EndereçoExisteException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
