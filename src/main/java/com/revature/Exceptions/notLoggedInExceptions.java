package com.revature.Exceptions;

public class notLoggedInExceptions extends AuthorizationException {
	
	private static final long serialVersionUID = 5659070462446728638L;

	public notLoggedInExceptions() {
		super();
	}

	public notLoggedInExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public notLoggedInExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public notLoggedInExceptions(String message) {
		super(message);
	}

	public notLoggedInExceptions(Throwable cause) {
		super(cause);
	}
}
