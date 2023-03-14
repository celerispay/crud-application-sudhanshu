package com.crud.customercrud.exception;

public class BatchJobException extends RuntimeException{

private String message;
	
    public BatchJobException(String message) {
        super(message);
        this.message = message;
    }
    public BatchJobException() {
    }
}
