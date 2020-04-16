package persistance.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.User;

public class UserDAO extends DAOConnect implements DAO<User>{

	public static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	
	User userFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("user_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("last_name");
		String username = resultSet.getString("username");
		return new User(id, firstName, surname, username);
	}
	
	@Override
	public List<User> readAll() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user");) {
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				users.add(userFromResultSet(resultSet));
			}
			return users;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return Collections.emptyList();
	}
	
	public User read(Long id) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE user_id = " + id.intValue());) {
			resultSet.next();
			return userFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public User create(User createUser) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO user(first_name, last_name, username) VALUES('" + 
										createUser.getFirstName() + "','" + createUser.getLastName() + "','" + 
										createUser.getUsername() + "')");
			
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	@Override
	public User update(User updateCustomer) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE user SET first_name ='" + updateCustomer.getFirstName() + "', last_name ='" + 
										updateCustomer.getLastName() + "', username ='" + updateCustomer.getUsername() + 
										"' WHERE user_id =" + updateCustomer.getUserID().intValue());
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
			statement.executeUpdate("DELETE * FROM user WHERE user_id = " + id.intValue());
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE * FROM user_password WHERE user_id = " + id.intValue());
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	
	public User readLast() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user ORDER BY user_id DESC LIMIT 1");) {
			resultSet.next();
			return userFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	

}
