package com.uiAutomatiom.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.uiAutomatiom.helper.logger.LogHelper;

public class DataBaseHelper {

	private static Logger log = LogHelper.getLogger(DataBaseHelper.class);
	private static String url = "jdbc:mysql://localhost/person";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "password";
	private static Connection connection;
	private static DataBaseHelper instance = null;

	public DataBaseHelper() {
		connection = getSingleInstanceConnection();
	}

	public static DataBaseHelper getInstance() {
		if (instance == null) {
			instance = new DataBaseHelper();
		}
		return instance;
	}

	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url, userName, password);
			} catch (SQLException e) {
				log.info("Failed to create data base connection.. " + e);
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not found..." + e);
		}
		return connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public static ResultSet getResultSet(String sqlQuery) {
		instance = DataBaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing query : " + sqlQuery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sqlQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
