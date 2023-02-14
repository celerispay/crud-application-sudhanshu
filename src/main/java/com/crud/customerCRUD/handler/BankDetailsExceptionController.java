package com.crud.customerCRUD.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.customerCRUD.customException.BankDetailsNotFoundException;
import com.crud.customerCRUD.customException.CustomerNotFoundException;

@ControllerAdvice
public class BankDetailsExceptionController {
	
	
	 @ExceptionHandler(value = CustomerNotFoundException.class)
	 public ResponseEntity<String> exception(BankDetailsNotFoundException exception) {
	      return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
	   }

}
