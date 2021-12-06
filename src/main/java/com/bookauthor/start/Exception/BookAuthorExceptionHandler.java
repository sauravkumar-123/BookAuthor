package com.bookauthor.start.Exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookAuthorExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {BookAuthorException.class})
	public ResponseEntity<Object> handleBookAuthorException(BookAuthorException bookAuthorException){
		ExceptionResponse response=new ExceptionResponse(ZonedDateTime.now(),false,bookAuthorException.getMessage(),null);
		if(null==bookAuthorException.httpStatus) {
		  return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	   return new ResponseEntity<>(response,bookAuthorException.httpStatus);
	}
    
    @ExceptionHandler(value= {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException nullPointerException){
    	ExceptionResponse response=new ExceptionResponse(ZonedDateTime.now(),false,nullPointerException.getMessage(),null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
