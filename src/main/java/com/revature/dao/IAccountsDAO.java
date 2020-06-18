package com.revature.dao;

import java.util.List;

import com.revature.Jacksontemplates.TransferTemplate;
import com.revature.Jacksontemplates.WithdrawalDepositTemplate;
import com.revature.models.Account;

public interface IAccountsDAO {
public int insert(Account a);//create
	
	public List<Account> findAll();//read
	
	public Account findById(int id);
	
	public List <Account> findByAccount_Status(int id);
	
	public List <Account>  findAccountByUser (int id );
	
	public int update(Account a);//update
	
	public int delete(int id);//delete

	public double withdraw(WithdrawalDepositTemplate wt);
	
	public double deposit(WithdrawalDepositTemplate wt);

	public double transfer(TransferTemplate tt);
	
	public int passTime(int numOfMonths);
}

