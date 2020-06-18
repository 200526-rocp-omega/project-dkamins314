package com.revature.models;

import java.util.Objects;

public class Users {

	private int id;
	private String username;
	private String password;
	private String first_Name;
	private String last_Name;
	private String email;
	private  Role role;
	
	
	
	
	public Users(int id, String username, String password, String first_Name, String last_Name, String email, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
		this.role = role;
	}
	
	
	public Users() {
		super();
		
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String firstName) {
		this.first_Name = firstName;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String lastName) {
		this.last_Name = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, first_Name, id, last_Name, password, role, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Users)) {
			return false;
		}
		Users other = (Users) obj;
		return Objects.equals(email, other.email) && Objects.equals(first_Name, other.first_Name) && id == other.id
				&& Objects.equals(last_Name, other.last_Name) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", first_Name=" + first_Name
				+ ", last_Name=" + last_Name + ", email=" + email + ", role=" + role + "]";
	}
	
	
	
	
	
}
