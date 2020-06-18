package com.revature.services;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.revature.Exceptions.notLoggedInExceptions;
import com.revature.Jacksontemplates.LoginTemplate;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;

import com.revature.models.Users;

//This service layer that is designed to enforce your business logic
//these are misc rules that define how your application will function
//All interaction with dao will be through this service layer -  separation of responsibility
//design of this layer up to developer - business logic arbitrary - this layer has most creativity
//starting point create crud method to interact withDAO
//extra methods to to enforce other feature ie login/logout
public class UserServices {
	
	private IUserDAO dao = new UserDAO();

	public int insert(Users u) {
		 return dao.insert(u) ;
	}
		
		public List<Users> findAll(){
			return dao.findAll();
		}

		public Users findById(int id){
			return dao.findById(id);
		}
		
		public Users findByUserName(String username){
			return dao.findByUserName(username);
		}
		
		public int update(Users u){
			return dao.update(u);
		}
		public Users login(LoginTemplate lt) {
			Users userFromDB = findByUserName(lt.getUsername());
			
			if (userFromDB==null) {
				return null;
			}
			if (userFromDB.getPassword().contentEquals(lt.getPassword())) {
				return userFromDB;
			}
			return null;
		}
		
		
		
		
		public int delete(int id){
			return 0;
		}
		public void logOut(HttpSession session) {
			
			if(session == null || session.getAttribute("currentUser") == null) {
				throw new notLoggedInExceptions("User must be logged in, in order to logout.");
			}
			
			session.invalidate();
		}
	}