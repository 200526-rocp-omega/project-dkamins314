package com.revature.Jacksontemplates;

import java.util.Objects;

public class WithdrawalDepositTemplate {

	private int id;
	private double amount;
	private double balance;
	
	

	public WithdrawalDepositTemplate() {
		super();
		
	}

	public WithdrawalDepositTemplate(int id, double amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCurrentBalance() {
		return balance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.balance = currentBalance;
	}

	
		
	@Override
	public int hashCode() {
		return Objects.hash(amount, balance, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof WithdrawalDepositTemplate)) {
			return false;
		}
		WithdrawalDepositTemplate other = (WithdrawalDepositTemplate) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id;
	}

	@Override
	public String toString() {
		return "WithdrawalDepositTemplate [id=" + id + ", amount=" + amount + "]";
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	
	
	
}
