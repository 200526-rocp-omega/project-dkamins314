package com.revature.Exceptions;

public class RoleNotAllowedException extends AuthorizationException {


	

	private static final long serialVersionUID = 4182851798615647549L;

	public RoleNotAllowedException() {
		
	}

	public RoleNotAllowedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	RoleNotAllowedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public RoleNotAllowedException(String arg0) {
		super(arg0);
		
	}

	public RoleNotAllowedException(Throwable arg0) {
		super(arg0);
		
	}

}
