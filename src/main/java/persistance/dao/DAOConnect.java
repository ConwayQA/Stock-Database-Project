package persistance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnect {
	
	
	private static String jdbcConnectionUrl = "jdbc:mysql://34.76.51.174:3306/ims";
	private static String username = null;
	private static String password = null;
	
	
	
	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setUsername(String username) {
		DAOConnect.username = username;
	}

	public static void setPassword(String password) {
		DAOConnect.password = password;
	}

	public Connection databaseConnect() throws SQLException {
		return (DriverManager.getConnection(jdbcConnectionUrl, username, password));
	}
	
	public void databaseClose(Connection connect) throws SQLException {
		connect.close();
	}

}
