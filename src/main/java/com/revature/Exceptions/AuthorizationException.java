package com.revature.Exceptions;

public class AuthorizationException extends RuntimeException {

	
	private static final long serialVersionUID = -4736455763417016733L;

	public AuthorizationException() {
		
	}

	public AuthorizationException(String message) {
		super(message);

	}

	public AuthorizationException(Throwable cause) {
		super(cause);
		
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
			}

	public AuthorizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
