package com.revature.Controllers;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.Exceptions.notLoggedInExceptions;
import com.revature.models.Users;
import com.revature.services.UserServices;

public class UserController  {

	private final UserServices userService = new UserServices();
	
	public boolean logOut(HttpSession session) {
		try {
			userService.logOut(session);
		} catch(notLoggedInExceptions e) {
			return false;
		}
		return true;
	}
	
	public Users FindByUserId(int id) {
		
		return userService.findById(id);
	}
	public List<Users> findAllUsers(){
		
		
		return userService.findAll();
		
	}
	public  int  UpdateUser (Users u) {
		
		return userService.update(u);
		
	}
	}

