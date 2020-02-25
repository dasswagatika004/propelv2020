package com.mvc.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	static Connection connection = null;

	public static Connection getConnection() throws IOException, Exception {
		try {
			// calling the method loadPropertiesFile()to get the details from
			// jdbc.properties
			Properties prop = loadPropertiesFile();

			// storing properties into variables

			String driverClass = prop.getProperty("ORACLEJDBC.JDBC_DRIVER");
			String url = prop.getProperty("ORACLEJDBC.DB_URL");
			String userName = prop.getProperty("ORACLEJDBC.USER");
			String password = prop.getProperty("ORACLEJDBC.PASS");

			// register jdbc driver
			Class.forName(driverClass);
			return DriverManager.getConnection(url, userName, password);

		} catch (SQLException e) {
			throw new Exception("Error connecting to the database", e);

		}
	}

	private static Properties loadPropertiesFile() throws IOException {
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jdbc.properties"));
		return prop;
	}

	public static void main(String[] args) throws Exception {
		try {
			connection = ConnectionFactory.getConnection();
			
			System.out.println("Connected to database");

		} catch (IOException e) {
			System.out.println(e);
		}

	}


}






