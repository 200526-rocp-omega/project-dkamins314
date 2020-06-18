package com.revature.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.ConnectionUtil;
import com.revature.models.Role;
import com.revature.models.Users;

public class UserDAO implements IUserDAO {

	@Override
	public int insert(Users u) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			 // create  statement object from ConnectionUtil
			String sql = "Insert  into Users (id,username,password, first_name, last_name,email,role_id) Values(?,?,?,?,?,?,?,?)";
			//The below is another way of line above
			//String columns = "username,password, first_name, last_name,email,role_id";
			//	String sql = "Insert  into Users ("+ columns + ") Values(?,?,?,?,?,?,?)";
			
			PreparedStatement stmnt = conn.prepareStatement(sql);
			//assigning placeholder '?' values prevent sql injection
			stmnt.setInt(1, u.getId());
			stmnt.setString(1, u.getUsername());
			stmnt.setString(2, u.getPassword());
			stmnt.setString(3, u.getFirst_Name());
			stmnt.setString(4, u.getLast_Name());
			stmnt.setString(5, u.getEmail());
			stmnt.setInt(6, u.getRole().getId());
			
			return stmnt.executeUpdate(sql);
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return 0;
	}
	
	@Override
	public List<Users> findAll() {
		List<Users> allUsers = new ArrayList<>();
		
		//below is a try with resources block
		//it allows instantiation of some variables for use only inside the try block
		//at the end it will be automatically invoke the close() method
		
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql ="Select * from Users INNER Join ROLES ON Users.role_id = Roles.Role_id";
			
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int role_id =rs.getInt("role_id");
				String role_Type = rs.getString("role_type");		
				Role role = new Role(role_id, role_Type);
				Users u = new Users(id,username,password,firstname,lastname,email, role);
				
				allUsers.add(u);
			}
		}catch(SQLException e)
		
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
		return allUsers;
	}

	@Override
	public Users findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "Select * from Users INNER Join ROLES ON Users.role_id = Roles.Role_id WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
				String username =rs.getString("username");
				String password = rs.getString("password");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int role_id =rs.getInt("role_id");
				String role_Type = rs.getString("role_type");		
				Role role = new Role(role_id, role_Type);
				return new Users(id,username,password,firstname,lastname,email, role);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	

	@Override
	public Users findByUserName(String username) {
		
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "Select * from Users INNER Join ROLES ON Users.role_id = Roles.Role_id WHERE username = ?";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, username);
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("password");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int role_id =rs.getInt("role_id");
				String role_Type = rs.getString("role_type");		
				Role role = new Role(role_id, role_Type);
				return new Users(id,username,password,firstname,lastname,email, role);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	public int update(Users u) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			 // create  statement object from ConnectionUtil
						
			String sql = "Update Users SET username=?,password=?,first_name=?, last_name=?,email=?,role_id=?  WHERE id= ?";
					
			PreparedStatement stmnt = conn.prepareStatement(sql);
			//assigning placeholder '?' values prevent sql injection
			stmnt.setString(1, u.getUsername());
			stmnt.setString(2, u.getPassword());
			stmnt.setString(3, u.getFirst_Name());
			stmnt.setString(4, u.getLast_Name());
			stmnt.setString(5, u.getEmail());
			stmnt.setInt(6, u.getRole().getId());
			stmnt.setInt(7,   u.getId());
			  
			if (stmnt.executeUpdate() != 0){
				System.out.println(u);
			return  1;
			} else {
				return 0;
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return 0;
	}
	@Override
	public int delete(int id) {
		try  (Connection conn = ConnectionUtil.getConnection()) {
			String deleteSql ="Delete from Users Where id =?";
            PreparedStatement stmt = conn.prepareStatement(deleteSql);
            stmt.setInt(1, id);
            
           stmt.executeUpdate(deleteSql);
            
           System.out.println("User with  " +id +"  " +"has been deleted");
            
            } catch(SQLException e) {
            e.printStackTrace();
        }
        
       return 0;
	
	}

	}
