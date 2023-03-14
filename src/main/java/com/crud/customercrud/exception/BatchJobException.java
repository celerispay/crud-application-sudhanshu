package com.crud.customercrud.exception;

public class BatchJobException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public BatchJobException(String message) {
		super(message);
		this.message = message;
	}

	public BatchJobException() {
	}
}
