package com.crud.exception;

public class BankDetailsAlreadyExistsException extends RuntimeException {

	private String message;

	public BankDetailsAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public BankDetailsAlreadyExistsException() {
	}
}
