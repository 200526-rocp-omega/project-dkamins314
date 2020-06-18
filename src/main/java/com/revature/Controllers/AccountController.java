package com.revature.Controllers;

import java.util.List;

import com.revature.Jacksontemplates.TransferTemplate;
import com.revature.Jacksontemplates.WithdrawalDepositTemplate;
import com.revature.models.Account;
import com.revature.services.AccountServices;

public class AccountController {
	
	

	private final AccountServices accountService = new AccountServices();
	
	
	
		public List<Account> findAllAccounts(){
					
		return accountService.findAll();
				
	}
	
	public Account findAccountById(int id) {
		
		return accountService.findById(id);
	}
	
		public List<Account> findAccountByOwner(int id) {
				
		return accountService.findAccountByOwner(id);
		
	}
		public List <Account> findByAccount_Status(int id) {
			
			return accountService.findByAccount_Status( id);
	
	
		}
		public  int UpdateAccount (Account a) {
			
			return accountService.update(a);
			
		}
		public  int submitAccount (Account a) {
					
					return accountService.insert(a);
		};
		
		public  double withdraw (WithdrawalDepositTemplate wt) {
			
			return accountService.withdraw( wt);
		}
		
		public  double deposit (WithdrawalDepositTemplate wt) {
					
					return accountService.deposit(wt);
		};
		public  double transfer (TransferTemplate tt) {
			
			return accountService.transfer(tt);
		};
		
		public  int PassTime (int numOfMonths) {
					
			return accountService.passTime(numOfMonths);
					
				}
		
		public  int deleteAccount (int id) {
					
					return accountService.delete(id);
		};
}
			

