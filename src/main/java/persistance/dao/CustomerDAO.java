package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Customer;

public class CustomerDAO extends DAOConnect implements DAO<Customer>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDAO.class);
	
	Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("customer_id");
		String firstName = resultSet.getString("first_name");
		String lastName = resultSet.getString("last_name");
		String address = resultSet.getString("address");
		String email = resultSet.getString("email");
		String postcode = resultSet.getString("postcode");
		Long userID = resultSet.getLong("user_id");
		return new Customer(id, firstName, lastName, address, email, postcode, userID);
	}

	@Override
	public List<Customer> readAll() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");) {
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(customerFromResultSet(resultSet));
			}
			return customers;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return Collections.emptyList();
	}
	
	@Override
	public Customer read(Long id) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");) {
			statement.setInt(1, id.intValue());
			try (ResultSet resultSet = statement.executeQuery();){
			resultSet.next();
			return customerFromResultSet(resultSet);
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Customer create(Customer createCustomer) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("INSERT INTO customers(first_name, last_name, address, email, postcode, user_id) VALUES(?,?,?,?,?,?)");) {
			statement.setString(1, createCustomer.getFirstName());
			statement.setString(2, createCustomer.getLastName());
			statement.setString(3, createCustomer.getAddress());
			statement.setString(4, createCustomer.getEmail());
			statement.setString(5, createCustomer.getPostcode());
			statement.setInt(6, createCustomer.getUserID().intValue());
			statement.executeUpdate();
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Customer update(Customer updateCustomer) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("UPDATE customers SET first_name = ?, last_name = ?, address = ?, email = ?, postcode = ?, user_id = ? WHERE customer_id = ?");) {
			statement.setString(1, updateCustomer.getFirstName());
			statement.setString(2, updateCustomer.getLastName());
			statement.setString(3, updateCustomer.getAddress());
			statement.setString(4, updateCustomer.getEmail());
			statement.setString(5, updateCustomer.getPostcode());
			statement.setInt(6, updateCustomer.getUserID().intValue());
			statement.setInt(7, updateCustomer.getId().intValue());
			statement.executeUpdate();
			return read(updateCustomer.getId());
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?");) {
			statement.setInt(1, id.intValue());
			statement.executeUpdate();
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Customer readLast() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY customer_id DESC LIMIT 1");) {
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

}
