package com.crud.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

	private String message;

	public CustomerAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public CustomerAlreadyExistsException() {
	}
}
