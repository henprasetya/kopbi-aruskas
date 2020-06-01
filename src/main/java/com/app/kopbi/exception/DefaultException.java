package com.app.kopbi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DefaultException extends RuntimeException {
	public DefaultException() {
	}
	
	public DefaultException(String message) {
		super(message);
	}

	public DefaultException(String message, Throwable cause) {
		super(message, cause);
	}
}
