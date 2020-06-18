package com.revature.models;

import java.util.Objects;

public class Role {
	private int id;
	private String role;
	
	
	//constructor1
	public Role() {
		super();
		
	}


//constructor2
	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

//getters and setters

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

//over ride hashcode - equals Tostring methods

	@Override
	public int hashCode() {
		return Objects.hash(id, role);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;
		return id == other.id && Objects.equals(role, other.role);
	}



	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	
	
	
	
}
