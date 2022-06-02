package com.example.eccomerce.exception;

public class EndereçoNotException extends Exception {

private String message;
	
	public EndereçoNotException(String message) {
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
