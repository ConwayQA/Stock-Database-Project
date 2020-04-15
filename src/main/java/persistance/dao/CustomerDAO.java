package persistance.dao;

import java.sql.Connection;
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
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(customerFromResultSet(resultSet));
			}
			return customers;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	@Override
	public Customer read(Long id) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
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
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO customers(first_name, last_name, address, email, postcode) VALUES('" + 
										createCustomer.getFirstName() + "','" + createCustomer.getLastName() + "','" + 
										createCustomer.getAddress() + "','" + createCustomer.getEmail() + "','" + 
										createCustomer.getPostcode() + "')");
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Customer update(Customer updateCustomer) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE customers SET first_name ='" + updateCustomer.getFirstName() + "', last_name ='" + 
										updateCustomer.getLastName() + "', address ='" + updateCustomer.getAddress() + "', email ='" + 
										updateCustomer.getEmail() + "', postcode ='" + updateCustomer.getPostcode() + "' WHERE customer_id =" + 
										updateCustomer.getId());
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE * FROM customers WHERE customer_id = " + id);
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Customer readLast() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY customer_id DESC LIMIT 1");
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

}
