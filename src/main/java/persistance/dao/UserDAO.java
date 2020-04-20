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
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");) {
			statement.setInt(1, id.intValue());
			try (ResultSet resultSet = statement.executeQuery();) {
			resultSet.next();
			return userFromResultSet(resultSet);
			}catch (SQLException sqle) {
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
	public User create(User createUser) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("INSERT INTO user(first_name, last_name, username) VALUES(?,?,?)");
				PreparedStatement statement2 = connection.prepareStatement("INSERT INTO user_password(user_id, password) VALUES(?,AES_ENCRYPT(?,?)");) {
			statement.setString(1, createUser.getFirstName());
			statement.setString(2, createUser.getLastName());
			statement.setString(3, createUser.getUsername());
			statement.executeUpdate();
			createUser = readLast();
			statement2.setInt(1, createUser.getUserID().intValue());
			statement2.setString(2, createUser.getPassword());
			statement.setBytes(3, createUser.getUsername().getBytes());
			statement2.executeUpdate();
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	@Override
	public User update(User updateUser) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("UPDATE user SET first_name = ?, last_name = ?, username = ? WHERE user_id = ?");
				PreparedStatement statement2 = connection.prepareStatement("UPDATE user_password SET user_id = ?, password = AES_ENCRYPT(?,?)");) {
			statement.setString(1, updateUser.getFirstName());
			statement.setString(2, updateUser.getLastName());
			statement.setString(3, updateUser.getUsername());
			statement.setInt(4, updateUser.getUserID().intValue());
			statement.executeUpdate();
			statement2.setInt(1, updateUser.getUserID().intValue());
			statement2.setString(2, updateUser.getPassword());
			statement2.setBytes(3, updateUser.getUsername().getBytes());
			statement2.executeUpdate();
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("DELETE * FROM user WHERE user_id = ?");) {
			statement.setInt(1, id.intValue());
			statement.executeUpdate();
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("DELETE * FROM user_password WHERE user_id = ?");) {
			statement.setInt(1, id.intValue());
			statement.executeUpdate();
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
