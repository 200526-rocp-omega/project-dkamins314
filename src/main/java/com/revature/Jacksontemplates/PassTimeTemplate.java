package com.revature.Jacksontemplates;

import java.util.Objects;

public class PassTimeTemplate {
	
	int numOfMonths;

	public PassTimeTemplate() {
		super();
		
		
		
	}

	public PassTimeTemplate(int numOfMonths) {
		super();
		this.numOfMonths = numOfMonths;
	}

	public int getNumOfMonths() {
		return numOfMonths;
	}

	public void setNumOfMonths(int numOfMonths) {
		this.numOfMonths = numOfMonths;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numOfMonths);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PassTimeTemplate)) {
			return false;
		}
		PassTimeTemplate other = (PassTimeTemplate) obj;
		return numOfMonths == other.numOfMonths;
	}

	@Override
	public String toString() {
		return "PassTimeTemplate [numOfMonths=" + numOfMonths + "]";
	}
	
	

}
