package mainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import mainer.DBConfig;

public abstract class DBConnection {
	
	private static Connection conn;
	private static Statement stmt;
	
	public DBConnection() throws SQLException{
		conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
		setStmt(conn.createStatement());
	}
	
	public void close() throws SQLException{
		conn.close();
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static void setStmt(Statement stmt) {
		DBConnection.stmt = stmt;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		DBConnection.conn = conn;
	}
	
	

}
