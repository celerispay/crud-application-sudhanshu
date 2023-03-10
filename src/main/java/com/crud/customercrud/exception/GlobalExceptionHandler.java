package com.crud.customercrud.exception;

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

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> NotFoundException(NotFoundException ex, WebRequest request) {
		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorInfo> genericException(Exception ex, HttpServletRequest request) {

		log.error("exception : " + ex.getLocalizedMessage() + " for " + request.getRequestURI());

		return new ResponseEntity<>(
				ErrorInfo.builder().message(ex.getLocalizedMessage())
						.message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
						.details(request.getRequestURI())
						.build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
