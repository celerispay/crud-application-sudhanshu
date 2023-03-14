package com.crud.customercrud.exception;

public class AlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public AlreadyExistException(String message) {
		super(message);
		this.message = message;
	}

	public AlreadyExistException() {
	}

}
