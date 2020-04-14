package persistance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnect {
	
	private String jdbcConnectionUrl = "jdbc:mysql://34.77.228.162:3306/ims";
	private String username = "root";
	private String password = "root";
	
	public Connection databaseConnect() throws SQLException {
		return (DriverManager.getConnection(jdbcConnectionUrl, username, password));
	}
	
	public void databaseClose(Connection connect) throws SQLException {
		connect.close();
	}

}
