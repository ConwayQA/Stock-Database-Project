package persistance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(itemFromResultSet(resultSet));
			}
			return items;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return Collections.emptyList();
	}
	
	@Override
	public Item read(Long id) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE item_id = ?");) {
			statement.setInt(1, id.intValue());
			ResultSet resultSet = statement.executeQuery();
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
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("INSERT INTO items(name, price, genre, min_players, max_players, avg_play_time, user_id) VALUES(?,?,?,?,?,?)");) {
			statement.setString(1, createItem.getName());
			statement.setDouble(2, createItem.getPrice().doubleValue());
			statement.setString(3, createItem.getGenre());
			statement.setInt(4, createItem.getMinPlayers().intValue());
			statement.setInt(5, createItem.getMaxPlayers().intValue());
			statement.setInt(6, createItem.getAvgPlayTime().intValue());
			statement.setInt(7, createItem.getUserID().intValue());
			statement.executeUpdate();
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Item update(Item updateItem) {
		try (Connection connection = databaseConnect();
				PreparedStatement statement = connection.prepareStatement("UPDATE items SET name = ?, price = ?, genre = ?, min_players = ?, max_players = ?, avg_play_time = ?, user_id = ? WHERE item_id = ?");) {
			statement.setString(1, updateItem.getName());
			statement.setDouble(2, updateItem.getPrice().doubleValue());
			statement.setString(3, updateItem.getGenre());
			statement.setInt(4, updateItem.getMinPlayers().intValue());
			statement.setInt(5, updateItem.getMaxPlayers().intValue());
			statement.setInt(6, updateItem.getAvgPlayTime().intValue());
			statement.setInt(7, updateItem.getUserID().intValue());
			statement.setInt(8, updateItem.getId().intValue());
			statement.executeUpdate();
			return read(updateItem.getId());
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try (Connection connection = databaseConnect(); PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE item_id = ?");) {
			statement.setInt(1, id.intValue());
			statement.executeUpdate();
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Item readLast() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

}
