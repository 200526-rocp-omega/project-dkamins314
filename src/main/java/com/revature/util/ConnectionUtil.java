package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection conn = null;
	
	
	private ConnectionUtil() {
		super();
	}
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				conn = DriverManager.getConnection(
			"jdbc:oracle:thin:@oracle-training.c11ooii9xvh1.us-east-2.rds.amazonaws.com:1521:ORCL",
			"beaver",
			"chew");//hard coded password very unsafe - use environmental variables
			}catch(SQLException e ) {
				e.printStackTrace();
			}
			
			
		}catch(ClassNotFoundException e) {
			System.out.println("Did not find Oracle JDBC Driver Class!");
		}
		return conn;
	}
}
