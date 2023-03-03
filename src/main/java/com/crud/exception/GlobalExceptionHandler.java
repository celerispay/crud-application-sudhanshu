package com.crud.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> CustomerNotFoundException(CustomerNotFoundException ex , WebRequest request) {
		return new ResponseEntity<>("User with above id is not found ", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CustomerNotFoundException.class)
	 public ResponseEntity<String> exception(BankDetailsNotFoundException exception) {
	      return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
	   }
	
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> genericException(
             Exception ex, 
             HttpServletRequest request){
        
        log.error("exception : "+ 
                 ex.getLocalizedMessage()+ 
                 " for "+ 
                 request.getRequestURI() );
        
        return new ResponseEntity<>(
             ApiError.builder()
             .errorMessage(ex.getLocalizedMessage()) 
             .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
             .request(request.getRequestURI())
             .requestType(request.getMethod())
             .customMessage("Could not process request")
             .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
