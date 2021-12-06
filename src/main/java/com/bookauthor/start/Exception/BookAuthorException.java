package com.bookauthor.start.Exception;

import org.springframework.http.HttpStatus;

public class BookAuthorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    
	public HttpStatus httpStatus=null;
	
	public BookAuthorException() {
		super();
	}

	public BookAuthorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookAuthorException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookAuthorException(String message) {
		super(message);
	}
	
	public BookAuthorException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus=httpStatus;
	}

	public BookAuthorException(Throwable cause) {
		super(cause);
	}

}
