package com.revature.Jacksontemplates;

import java.util.Objects;

public class TransferTemplate {
	
	private int sourceAccountId;
	private int targetAccountId;
	private double sourceAccountBalance;
	private double targetAccountBalance;
	private double amount;
	
	
	
	
	public TransferTemplate() {
		super();
		
	}



	public TransferTemplate(int sourceAccountId, int targetAccountId, double sourceAccountBalance,
			double targetAccountBalance, double amount) {
		super();
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.sourceAccountBalance = sourceAccountBalance;
		this.targetAccountBalance = targetAccountBalance;
		this.amount = amount;
	}





	public double getSourceAccountBalance() {
		return sourceAccountBalance;
	}








	public void setSourceAccountBalance(double sourceAccountBalance) {
		this.sourceAccountBalance = sourceAccountBalance;
	}








	public double getTargetAccountBalance() {
		return targetAccountBalance;
	}








	public void setTargetAccountBalance(double targetAccountBalance) {
		this.targetAccountBalance = targetAccountBalance;
	}








	public int getSourceAccountId() {
		return sourceAccountId;
	}




	public void setSourceAccountId(int sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}




	public int getTargetAccountId() {
		return targetAccountId;
	}




	public void setTargetAccountId(int targetAccountId) {
		this.targetAccountId = targetAccountId;
	}




	public double getAmount() {
		return amount;
	}




	public void setAmount(double amount) {
		this.amount = amount;
	}



	@Override
	public int hashCode() {
		return Objects.hash(amount, sourceAccountBalance, sourceAccountId, targetAccountBalance, targetAccountId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TransferTemplate)) {
			return false;
		}
		TransferTemplate other = (TransferTemplate) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Double.doubleToLongBits(sourceAccountBalance) == Double.doubleToLongBits(other.sourceAccountBalance)
				&& sourceAccountId == other.sourceAccountId
				&& Double.doubleToLongBits(targetAccountBalance) == Double.doubleToLongBits(other.targetAccountBalance)
				&& targetAccountId == other.targetAccountId;
	}



	@Override
	public String toString() {
		return "TransferTemplate [sourceAccountId=" + sourceAccountId + ", targetAccountId=" + targetAccountId
				+ ", sourceAccountBalance=" + sourceAccountBalance + ", targetAccountBalance=" + targetAccountBalance
				+ ", amount=" + amount + "]";
	}




	
	
	
	
	
}