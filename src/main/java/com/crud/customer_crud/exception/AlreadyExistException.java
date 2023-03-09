package com.crud.customer_crud.exception;

public class AlreadyExistException extends RuntimeException{
	
	private String message;
	
    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
    public AlreadyExistException() {
    }
	
}
