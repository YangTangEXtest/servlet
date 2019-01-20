package com.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConn {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/choicetest";
	static final String user = "root";
	static final String pass = "sa";
	public Connection getConn() throws SQLException, ClassNotFoundException{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(DB_URL,user,pass);	
		return conn;
	}
}
