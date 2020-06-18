package com.revature.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.Jacksontemplates.*;
import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountsDAO {
	


	@Override
	public int insert(Account a) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			 // create  statement object from ConnectionUtil
			String sql = "Insert  into Accounts (balance,Account_Status,Account_Type) Values(?,?,?)";
			
			
			PreparedStatement stmnt = conn.prepareStatement(sql);
			//assigning placeholder '?' values prevent sql injection
			stmnt.setDouble(1, a.getBalance());
			stmnt.setInt(2, a.getStatus().getStatusId());
			stmnt.setInt(3, a.getType().getTypeId());
			
			return stmnt.executeUpdate(sql);
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return 0;
	}
	
	@Override
	public List<Account> findAll() {
		List<Account> allAccounts = new ArrayList<>();
		
		//below is a try with resources block
		//it allows instantiation of some variables for use only inside the try block
		//at the end it will be automatically invoke the close() method
		
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql ="Select * from ACCOUNTS INNER Join ACCOUNT_STATUS ON ACCOUNTS.STATUS_ID = ACCOUNT_STATUS.ID"
					+ " INNER Join  ACCOUNT_TYPE On ACCOUNTS.TYPE_ID = ACCOUNT_TYPE.ID ";
			
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				int accountId = rs.getInt("id");
				Double balance = rs.getDouble("balance");
				int account_type_id =rs.getInt("type_id");
				String account_type = rs.getString("type");		
				AccountType account_Type = new AccountType(account_type_id, account_type);
				int statusId =rs.getInt("status_id");
				String account_status = rs.getString("status");		
				AccountStatus account_Status = new AccountStatus(statusId, account_status);
				Account a = new Account (accountId,balance,  account_Status,account_Type);
				
				allAccounts.add(a);
			}
		}catch(SQLException e)
		
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
		return allAccounts;
	}

	@Override
	public Account findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql ="Select * from Accounts INNER Join Account_Type ON Accounts.status_id = Account_Status.id "
					+ "Inner Join  Account_Type On Account.type_id = Account_Type.id  Where id =?";
			
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
            	int accountId = rs.getInt("accountid");
				Double balance = rs.getDouble("balance");
				int account_type_id =rs.getInt("type_id");
				String account_type = rs.getString("type");		
				AccountType account_Type = new AccountType(account_type_id, account_type);
				int statusId =rs.getInt("status_id");
				String account_status = rs.getString("status");		
				AccountStatus account_Status = new AccountStatus(statusId, account_status);
				return  new Account (accountId,balance, account_Status,account_Type);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	 
	@Override
	public List  <Account> findByAccount_Status(int id) {
		List<Account> accStatus = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql ="Select * from Account_Status INNER Join Accounts ON Accounts_Status.id = Account.Status_id "
					+ "Inner Join  Account_Type On Account.Type_Id = Account_Type.Id  Where Account_Status.id =?";
			
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
            	int accountId = rs.getInt("accountid");
       
				Double balance = rs.getDouble("balance");
				int account_type_id =rs.getInt("type_id");
				String account_type = rs.getString("type");	
				String account_status = rs.getString("status");
				AccountType account_Type = new AccountType(account_type_id, account_type);
				int statusId =rs.getInt("status_id");
				AccountStatus account_Status = new AccountStatus(statusId, account_status);
			   Account as = new Account (accountId,balance, account_Status,account_Type);
			    accStatus.add(as);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return accStatus;
	}
	

	@Override
	public List <Account> findAccountByUser(int id) {
		List<Account> ownersAccounts = new ArrayList<>();
        
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "Select * from Users Inner Join Users_Accounts on Users_Accounts.user_id = Users.id "
            		+ "Inner Join Accounts on Accounts.id = User_Accounts.Account_id   WHERE Users.id = ?";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setInt(1, id);
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()) {
			
				
            	int accountId = rs.getInt("accountid");
				Double balance = rs.getDouble("balance");
				int account_type_id =rs.getInt("type_id");
				String account_type = rs.getString("type");		
				AccountType account_Type = new AccountType(account_type_id, account_type);
				int statusId =rs.getInt("status_id");
				String account_status = rs.getString("status");	
				AccountStatus account_Status = new AccountStatus(statusId, account_status);
				Account oa = new Account (accountId,balance, account_Status,account_Type);
            
				ownersAccounts.add  (oa);
				 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
		return ownersAccounts;
        
       //return oa;
	}

	@Override
	public int update(Account a) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			 // create  statement object from ConnectionUtil
						
			String sql = "Update Accounts SET balance=?,staus_id=?, typeid=?  WHERE id= ?";
					
			PreparedStatement stmnt = conn.prepareStatement(sql);
			//assigning placeholder '?' values prevent sql injection
			stmnt.setDouble(1, a.getBalance());
			stmnt.setInt(2, a.getStatus().getStatusId());
			stmnt.setInt(3, a.getType().getTypeId());
			stmnt.setInt(4, a.getAccountId());
			  
			if (stmnt.executeUpdate() != 0){
				System.out.println(a);
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
	public double withdraw (WithdrawalDepositTemplate wt) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
						
			String sql = "Update Accounts SET balance=?  WHERE id= ?";
					
			PreparedStatement stmnt = conn.prepareStatement(sql);
			//assigning placeholder '?' values prevent sql injection
			stmnt.setDouble(1, wt.getBalance());
			stmnt.setInt(2, wt.getId());
			  
			if (stmnt.executeUpdate() != 0){
				System.out.println("The amount of $" + wt.getAmount() +"has been withdrawn from your account" );
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
	public double deposit(WithdrawalDepositTemplate wt) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			 // create  statement object from ConnectionUtil
						
			String sql = "Update Accounts SET balance=?,  WHERE id= ?";
					
			PreparedStatement stmnt = conn.prepareStatement(sql);
			//assigning placeholder '?' values prevent sql injection
			stmnt.setDouble(1, wt.getBalance());
			stmnt.setInt(2, wt.getId());
			
			  
			if (stmnt.executeUpdate() != 0){
				System.out.println("The amount of $" + wt.getAmount() +"has been added to your account" );
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
			String deleteSql ="Delete from Account Where id =?";
            PreparedStatement stmt = conn.prepareStatement(deleteSql);
            stmt.setInt(1, id);
            
           stmt.executeUpdate(deleteSql);
            
           System.out.println("Account with  " +id +"  " +"has been deleted");
            
            } catch(SQLException e) {
            e.printStackTrace();
        }
        
       return 0;
	
	}

	@Override
	public double transfer(TransferTemplate tt) {

		try (Connection conn = ConnectionUtil.getConnection()){
			
			 // create  statement object from ConnectionUtil
						
			 String sql = "Update Accounts SET balance=?,  WHERE id= ?";
			 String sql1 = "Update Accounts SET balance=?,  WHERE id= ?";
		
					
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setDouble(1, tt.getSourceAccountBalance());
			stmnt.setInt(2,tt.getSourceAccountId());
			
			PreparedStatement stmnt1 = conn.prepareStatement(sql1);
			//assigning placeholder '?' values prevent sql injection
			stmnt1.setDouble(1, tt.getTargetAccountBalance());
			stmnt1.setInt(2,tt.getTargetAccountId());
			
			  
			if (stmnt.executeUpdate() != 0 && stmnt1.executeUpdate() != 0){
				System.out.println("The amount of $" + tt.getAmount() +"has been transferred from Acvcount #" +tt.getSourceAccountId()
						+ " to Account #" + tt.getTargetAccountId() );
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
	public int passTime(int numOfMonths) {
	
		return numOfMonths;
	}

	}































