package com.ersspring.exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalException {


	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError)error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		  
		    return new ResponseEntity<Object>(errors, headers, status);
	}


	
	@ExceptionHandler(InvalidLogin.class)
	protected ResponseEntity<Object> handleBookNotFoundException(InvalidLogin ex) { 
		 Map<String, String> errors = new HashMap<>();										
		 System.out.println(errors);
		 errors.put("date", LocalDate.now()+"");
		 errors.put("error", ex.getMessage());
		 return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
