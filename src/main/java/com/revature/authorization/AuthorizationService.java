package com.revature.authorization;

import javax.servlet.http.HttpSession;

import com.revature.Exceptions.RoleNotAllowedException;
import com.revature.Exceptions.notLoggedInExceptions;
import com.revature.models.Users;

public class AuthorizationService {	
	
	public static void  guard(HttpSession session, String...roles) {
	
	Users currentUser = session ==null?null: (Users) session.getAttribute("currentUser");
	if(session == null|| currentUser == null) {
		throw new notLoggedInExceptions();
	}
	 
	boolean found =false;
	//(role object from currentUser ) - get role string name
	String role = currentUser.getRole().getRole();
	 for(String allowedRole : roles) {
		 
		 if(allowedRole.contentEquals(role)) {
			 found = true;
		 }
	}
	 
	 if (!found) {
		 throw new RoleNotAllowedException();
	 }
}
	
	 public static void  guard(HttpSession session,int id, String...roles) {
			
			Users currentUser = session ==null?null: (Users) session.getAttribute("currentUser");
			if(session == null|| currentUser == null) {
				throw new notLoggedInExceptions();
			}
			 
			boolean found =false;
			//(role object from currentUser ) - get role string name
			String role = currentUser.getRole().getRole();
			
			 for(String allowedRole : roles) {
				 
				 if(allowedRole.contentEquals(role)) {
					 found = true;
				 }
				 
			}
			 if (!found) {
				 throw new RoleNotAllowedException();
			 }
	
	 }
	 
	 public static void guard(HttpSession session) {
			Users currentUser = session ==null?null: (Users) session.getAttribute("currentUser");
			if(session == null|| currentUser == null) {
				throw new notLoggedInExceptions();
			}
	 }

}
