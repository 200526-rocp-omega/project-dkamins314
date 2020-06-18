package com.revature.services;
import com.revature.models.Account;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.revature.Exceptions.notLoggedInExceptions;
import com.revature.Jacksontemplates.TransferTemplate;
import com.revature.Jacksontemplates.WithdrawalDepositTemplate;
import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountsDAO;


public class AccountServices {
	
		
		private IAccountsDAO dao = new AccountDAO();

		public int insert(Account a) {
			 return dao.insert(a) ;
		}
			
			public List<Account> findAll(){
				return dao.findAll();
			}

			public Account findById(int id){
				return dao.findById(id);
			}
			
			public List<Account> findAccountByOwner(int id){
				return dao.findAccountByUser( id);
			}
			public List<Account> findByAccount_Status(int id) {
				return dao.findByAccount_Status(id );
			}
			public int update(Account a){
				return dao.update(a);
			}
			public  double withdraw (WithdrawalDepositTemplate wt) {
				Account accountFromDB = findById(wt.getId());
				double origbal = wt.getBalance();
				double wda = wt.getAmount();	
					if(origbal> 5) {
						double newbal =(origbal - wda);
						accountFromDB.setBalance(newbal);
					}
				return dao.withdraw(wt);
	        }
	
	
			public  double deposit (WithdrawalDepositTemplate wt) {
				Account accountFromDB = findById(wt.getId());
				double origbal = wt.getBalance();
				double da = wt.getAmount();			
					if(da> 0) {
						double newbal =(origbal + da);
						accountFromDB.setBalance(newbal);
						return newbal;
					} else {
						
						accountFromDB.setBalance(origbal);
							
		         	}
				
				return dao.withdraw( wt);
              }
			
			public  double transfer (TransferTemplate tt) {
				Account sourceAccountFromDB = findById(tt.getSourceAccountId());
				Account targetAccountFromDB = findById(tt.getTargetAccountId());
				double sorigbal = tt.getSourceAccountBalance();
				double torigbal = tt.getTargetAccountBalance();
				double transa = tt.getAmount();
				
					if(sorigbal>transa) {
						double sorNewbal =(sorigbal - transa);
						double tarNewbal =(torigbal + transa);
						sourceAccountFromDB.setBalance(sorNewbal);
						targetAccountFromDB.setBalance(tarNewbal);
						return  tarNewbal;
					} else {
						
						return dao.transfer(tt);
							
		         	}
				
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
			
			public int passTime(int numOfMonths) {
			AccountDAO ptd = new AccountDAO();
			List<Account>userAccs =  ptd.findAll();
			
			for (Account sa :userAccs) {
			
				if(sa.getType().getTypeId() ==2) {
					double newbal = sa.getBalance()+((sa.getBalance() * .05) * numOfMonths );
					sa.setBalance(newbal);
					ptd.update(sa);
				}
			}
			return (0);
			}
}















