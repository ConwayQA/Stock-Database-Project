package persistance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Order;

public class OrderDAO extends DAOConnect implements DAO<Order>{
	
	public static final Logger LOGGER = Logger.getLogger(ItemDAO.class);
	
	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = (long) resultSet.getInt("order_id");
		Long customerID = (long) resultSet.getInt("customer_id");
		List<Long> itemIDs = readItemIDs(id);
		BigDecimal totalPrice = BigDecimal.valueOf(resultSet.getDouble("total_price"));
		String date = resultSet.getDate("date_ordered").toString();
		return new Order(id, customerID, itemIDs, totalPrice, date);
	}

	@Override
	public List<Order> readAll() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	public Order readItem(Long id) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT FROM orders WHERE order_id = " + id);
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order createOrder) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO orders(order_id, customer_id, total_price, date_ordered) VALUES('" + 
										createOrder.getId() + "','" + createOrder.getCustomerID() + "','" + 
										createOrder.getTotalPrice() + "','" + createOrder.getDate() + "')");
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order updateOrder) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE orders SET name ='" + updateOrder.getId() + "', price ='" + 
										updateOrder.getCustomerID() + "', genre ='" + updateOrder.getTotalPrice() + "', min_players ='" + 
										updateOrder.getDate() + "' WHERE item_id =" + updateOrder.getId());
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
			statement.executeUpdate("DELETE FROM orders WHERE order_id = " + id);
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Order readLast() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT FROM items ORDER BY item_id DESC LIMIT 1");
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	public List<Long> readItemIDs(Long orderID) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT item_id FROM order_items WHERE order_id = " + orderID);
			List<Long> itemIDs = new ArrayList<>();
			while (resultSet.next()) {
				itemIDs.add(resultSet.getLong(1));
			}
			return itemIDs;
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
}
