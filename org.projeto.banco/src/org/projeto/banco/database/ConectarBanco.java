package org.projeto.banco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBanco {
	
	private final static String DB_USER = "gui";
	private final static String DB_PASS = "rock1109";
	private final static String DB_ADDRESS = "localhost";
	private final static String DB_SCHEMA = "BANCOJAVA";
	private final static String DB_PORT = "3306";
	
	public Connection getConnection() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+DB_ADDRESS+":"
			+DB_PORT+"/"+DB_SCHEMA, DB_USER, DB_PASS);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
