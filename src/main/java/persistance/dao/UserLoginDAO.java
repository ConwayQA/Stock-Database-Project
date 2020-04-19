package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import persistance.domain.User;

public class UserLoginDAO extends DAOConnect implements LoginDAO<User>{
	
public static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	
	User userFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("user_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("last_name");
		String username = resultSet.getString("username");
		return new User(id, firstName, surname, username);
	}

	@Override
	public User login(String username, String password) {
		User userLogin = null;
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");){
				statement.setString(1, username);
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				userLogin = userFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("SELECT AES_DECRYPT(password,?) FROM user_password WHERE user_id = ?");){
				statement.setBytes(1, username.getBytes());
				statement.setInt(2, userLogin.getUserID().intValue());
				ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			if (password.contentEquals(resultSet.getString(1))) {
				userLogin.setLoggedIn(true);
			}
			return userLogin;
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

}
