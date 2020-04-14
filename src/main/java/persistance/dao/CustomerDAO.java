package persistance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Customer;

public class CustomerDAO extends DAOConnect implements DAO<Customer>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDAO.class);
	
	Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("customer_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("last_name");
		String address = resultSet.getString("address");
		String email = resultSet.getString("email");
		String postcode = resultSet.getString("postcode");
		return new Customer(id, firstName, surname, address, email, postcode);
	}

	@Override
	public List<Customer> readAll() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(customerFromResultSet(resultSet));
			}
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	public Customer readCustomer(Long id) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE customer_id = " + id);
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Customer create(Customer createCustomer) {
		return null;
	}

	@Override
	public Customer update(Customer updateCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
