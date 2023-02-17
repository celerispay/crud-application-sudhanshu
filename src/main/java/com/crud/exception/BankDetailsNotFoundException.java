package com.crud.exception;

public class BankDetailsNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;
    public BankDetailsNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public BankDetailsNotFoundException() {
    }
	
	
}
