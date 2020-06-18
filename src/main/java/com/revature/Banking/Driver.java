package com.revature.Banking;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Role;
import com.revature.models.Users;
import com.revature.util.ConnectionUtil;

public class Driver {
	
	
	

	public static void main(String[] args) {

	ConnectionUtil.getConnection();
		
		IUserDAO dao = new UserDAO();
		
		for (Users u : dao.findAll()){
			System.out.println(u);
		}
	//	User user = new User(0,"useone", "password","first","last", "abc@dill.com", new Role (1,"Standard") );
	//	System.out.println(user);
	}

}