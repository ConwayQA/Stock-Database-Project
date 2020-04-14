package persistance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Item;

public class ItemDAO extends DAOConnect implements DAO<Item> {
	
	public static final Logger LOGGER = Logger.getLogger(ItemDAO.class);
	
	Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = (long) resultSet.getInt("item_id");
		String name = resultSet.getString("name");
		BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("price"));
		String genre = resultSet.getString("genre");
		Long minPlayers = (long) resultSet.getInt("min_players");
		Long maxPlayers = (long) resultSet.getInt("max_players");
		Long avgPlayTime = (long) resultSet.getInt("avg_play_time");
		return new Item(id, name, price, genre, minPlayers, maxPlayers,avgPlayTime);
	}

	@Override
	public List<Item> readAll() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(itemFromResultSet(resultSet));
			}
			return items;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	public Item readItem(Long id) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT FROM items WHERE item_id = " + id);
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Item create(Item createItem) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO items(name, price, genre, min_players, max_players, avg_play_time) VALUES('" + 
										createItem.getName() + "','" + createItem.getPrice() + "','" + 
										createItem.getGenre() + "','" + createItem.getMinPlayers() + "','" + 
										createItem.getMaxPlayers() + "','" + createItem.getAvgPlayTime() + "')");
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Item update(Item updateItem) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE items SET name ='" + updateItem.getName() + "', price ='" + 
										updateItem.getPrice() + "', genre ='" + updateItem.getGenre() + "', min_players ='" + 
										updateItem.getMinPlayers() + "', max_players ='" + updateItem.getMaxPlayers() + "', avg_play_time ='" + updateItem.getAvgPlayTime() + 
										"' WHERE item_id =" + updateItem.getId());
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM items WHERE item_id = " + id);
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Item readLast() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT FROM items ORDER BY item_id DESC LIMIT 1");
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

}
