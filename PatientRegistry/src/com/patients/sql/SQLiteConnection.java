package com.patients.sql;

import java.sql.*;

public class SQLiteConnection {
	private static final String DB_URL = "jdbc:sqlite:patientsregistry.db";
	
	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
