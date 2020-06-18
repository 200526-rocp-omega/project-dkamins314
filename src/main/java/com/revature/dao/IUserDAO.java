package com.revature.dao;

import java.util.List;

import com.revature.models.Users;

public interface IUserDAO {
public int insert(Users u);//create
	
	public List<Users> findAll();//read
	
	public Users findById(int id);
	
	public Users findByUserName(String username);
	
	public int update(Users u);//update
	
	public int delete(int id);//delete
	

}
