package persistance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
		LocalDate date = (resultSet.getDate("date_ordered")).toLocalDate();
		
		return new Order(id, customerID, itemIDs, totalPrice, date);
	}

	@Override
	public List<Order> readAll() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
			List<Order> orders = new ArrayList<>();
			Order tempOrder;
			while (resultSet.next()) {
				tempOrder = orderFromResultSet(resultSet);
				tempOrder.setItemIDs(readItemIDs(orderFromResultSet(resultSet).getId()));
				orders.add(tempOrder);
			}
			return orders;
		} catch(SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}
	
	@Override
	public Order read(Long id) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE order_id = " + id);
			Order tempOrder;
			resultSet.next();
			tempOrder = orderFromResultSet(resultSet);
			tempOrder.setItemIDs(readItemIDs(orderFromResultSet(resultSet).getId()));
			return tempOrder;
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order createOrder) {
		if (createOrder.getTotalPrice() == null) {
			createOrder.setTotalPrice(calcTotalPrice(createOrder.getItemIDs()));
		}
		if (createOrder.getDate() == null) {
			createOrder.setDate(LocalDate.now());
		}
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO orders(order_id, customer_id, total_price, date_ordered) VALUES('" + 
										createOrder.getId() + "','" + createOrder.getCustomerID() + "','" + 
										createOrder.getTotalPrice() + "','" + createOrder.getDate() + "')");
			
			writeOrderItems(createOrder);
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order updateOrder) {
		if (updateOrder.getTotalPrice() == null) {
			updateOrder.setTotalPrice(calcTotalPrice(updateOrder.getItemIDs()));
		}
		if (updateOrder.getDate() == null) {
			updateOrder.setDate(LocalDate.now());
		}
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE orders SET name ='" + updateOrder.getId() + "', price ='" + 
										updateOrder.getCustomerID() + "', genre ='" + updateOrder.getTotalPrice() + "', min_players ='" + 
										updateOrder.getDate() + "' WHERE item_id =" + updateOrder.getId());
			writeOrderItems(updateOrder);
			return readLast();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE * FROM orders WHERE order_id = " + id);
			statement.executeUpdate("DELETE * FROM order_items WHERE order_id = " + id);
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Order readLast() {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");
			Order tempOrder;
			resultSet.next();
			tempOrder = orderFromResultSet(resultSet);
			tempOrder.setItemIDs(readItemIDs(orderFromResultSet(resultSet).getId()));
			return tempOrder;
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
	
	public void writeOrderItems(Order writeItemsOrder) {
		try {
			Connection connection = databaseConnect();
			Statement statement = connection.createStatement();
			for (Long itemID:writeItemsOrder.getItemIDs()) {
				statement.executeUpdate("INSERT INTO order_items(order_id, item_id) VALUES('" + 
						writeItemsOrder.getId() + "','" + itemID + "')");
			}
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
	}
	
	public BigDecimal calcTotalPrice(List<Long> itemIDs) {
		BigDecimal total = new BigDecimal(0);
		for (Long id: itemIDs) {
			try {
				Connection connection = databaseConnect();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT price FROM items WHERE item_id = " + id + "LIMIT 1");
					total = total.add(BigDecimal.valueOf(resultSet.getLong(1)));
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
		}
		return total;
	}

	
}
