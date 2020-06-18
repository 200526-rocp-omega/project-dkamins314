package com.revature.models;


	import java.util.Objects;

	public class AccountStatus {

		
		 private int statusId; // primary key
		 
		private String account_status;

		  
		  
		  public AccountStatus() {
			super();
			
			
			
		}



		public AccountStatus(int statusId, String account_status) {
			super();
			this.statusId = statusId;
			this.setAccount_status(account_status);
		}



		public String getAccount_status() {
			return account_status;
		}



		public void setAccount_status(String account_status) {
			this.account_status = account_status;
		}
		  

		public int getStatusId() {
			return statusId;
		}



		public void setStatusId(int statusId) {
			this.statusId = statusId;
		}



		@Override
		public int hashCode() {
			return Objects.hash(account_status, statusId);
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof AccountStatus)) {
				return false;
			}
			AccountStatus other = (AccountStatus) obj;
			return Objects.equals(account_status, other.account_status) && statusId == other.statusId;
		}



		@Override
		public String toString() {
			return "AccountStatus [statusId=" + statusId + ", status=" + account_status + "]";
		}


		  
		  
	}



