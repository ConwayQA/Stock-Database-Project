package customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import interfaces.*;
import mainer.*;

public class CustomerRead extends DBConnection implements DBRead{
	
	public CustomerRead() throws SQLException {
		super();
	}

	public void read() throws SQLException {
		ResultSet newResultSet = DBConnection.getStmt().executeQuery("SELECT * FROM customers");
		while (newResultSet.next()) {
			String customerRecord = newResultSet.getString("first_name") + " " + newResultSet.getString("last_name");
			System.out.println(customerRecord);
		}
	}
}
