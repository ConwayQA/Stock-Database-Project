package persistance.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DAOConnect {
	
	public static final Logger LOGGER = Logger.getLogger(DAOConnect.class);
	
	static String jdbcConnectionUrl = "jdbc:mysql://34.76.51.174:3306/ims";
	public static String username = null;
	public static String password = null;
	
	

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
	
	public static void init(String jdbcConnectionUrl, String username, String password, String fileLocation) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement();) {
					statement.executeUpdate(string);
				}
			}
		} catch (SQLException | IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
	}

}
