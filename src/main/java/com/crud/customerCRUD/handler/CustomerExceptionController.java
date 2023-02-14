package com.crud.customerCRUD.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crud.customerCRUD.customException.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> exception(CustomerNotFoundException ex) {
		return new ResponseEntity<String>("User with above id is not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
