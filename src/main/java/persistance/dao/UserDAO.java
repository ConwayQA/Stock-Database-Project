package persistance.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import persistance.domain.User;

public class UserDAO extends DAOConnect{

	public static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	
	User userFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("user_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("last_name");
		String username = resultSet.getString("username");
		return new User(id, firstName, surname, username);
	}
	
	public User read(Long id) {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE customer_id = " + id);) {
			resultSet.next();
			return userFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	
	
}
