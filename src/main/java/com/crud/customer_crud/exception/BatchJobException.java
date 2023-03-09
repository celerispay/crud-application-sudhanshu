package com.crud.customer_crud.exception;

public class BatchJobException extends RuntimeException{

private String message;
	
    public BatchJobException(String message) {
        super(message);
        this.message = message;
    }
    public BatchJobException() {
    }
}
