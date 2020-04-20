package persistance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Order;

public class OrderDAO extends DAOConnect implements DAO<Order>{
	
	public static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
	
	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = (long) resultSet.getInt("order_id");
		Long customerID = (long) resultSet.getInt("customer_id");
		List<Long> itemIDs = readItemIDs(id);
		BigDecimal totalPrice = BigDecimal.valueOf(resultSet.getDouble("total_price"));
		LocalDate date = (resultSet.getDate("date_ordered")).toLocalDate();
		Long userID = (long) resultSet.getInt("user_id");
		
		return new Order(id, customerID, itemIDs, totalPrice, date, userID);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
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
		return Collections.emptyList();
	}
	
	@Override
	public Order read(Long id) {
		try (Connection connection = databaseConnect(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setInt(1, id.intValue());
			Order tempOrder = null;
			try (ResultSet resultSet = statement.executeQuery();) {
			resultSet.next();
			tempOrder = orderFromResultSet(resultSet);
			tempOrder.setItemIDs(readItemIDs(orderFromResultSet(resultSet).getId()));
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
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
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(customer_id, total_price, date_ordered, user_id) VALUES(?, ?, ?, ?)");) {
			statement.setInt(1, createOrder.getCustomerID().intValue());
			statement.setDouble(2, createOrder.getTotalPrice().doubleValue());
			statement.setDate(3, Date.valueOf(createOrder.getDate()));
			statement.setInt(4, createOrder.getUserID().intValue());
			statement.executeUpdate();
			createOrder = readLast();
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
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("UPDATE orders SET customer_id = ?, total_price = ?, date_ordered = ?, user_id = ? WHERE order_id = ? ");) {
			statement.setInt(1, updateOrder.getCustomerID().intValue());
			statement.setDouble(2, updateOrder.getTotalPrice().doubleValue());
			statement.setDate(3, Date.valueOf(updateOrder.getDate()));
			statement.setInt(4, updateOrder.getUserID().intValue());
			statement.setInt(5, updateOrder.getId().intValue());
			statement.executeUpdate();
			removeOrderItems(updateOrder);
			writeOrderItems(updateOrder);
			return read(updateOrder.getId());
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");
				PreparedStatement statement2 = connection.prepareStatement("DELETE FROM order_items WHERE order_id = ?");) {
			statement.setInt(1, id.intValue());
			statement2.setInt(1, id.intValue());
			statement.executeUpdate();
			statement2.executeUpdate();
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
	}
	
	public Order readLast() {
		try (Connection connection = databaseConnect(); Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
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
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("SELECT item_id FROM order_items WHERE order_id = ?");) {
			statement.setInt(1, orderID.intValue());
			try (ResultSet resultSet = statement.executeQuery();) {
			List<Long> itemIDs = new ArrayList<>();
			while (resultSet.next()) {
				itemIDs.add(resultSet.getLong(1));
			}
			return itemIDs;
			}catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
			return Collections.emptyList();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
		return Collections.emptyList();
	}
	
	public void writeOrderItems(Order writeItemsOrder) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement2 = connection.prepareStatement("INSERT INTO order_items(order_id, item_id) VALUES(?, ?)");) {
			for (Long itemID:writeItemsOrder.getItemIDs()) {
				statement2.setInt(1, writeItemsOrder.getId().intValue());
				statement2.setInt(2, itemID.intValue());
				statement2.executeUpdate();
			}
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
	}
	
	public void removeOrderItems(Order removeItemsOrder) {
		try (Connection connection = databaseConnect(); 
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE order_id = ?");) {
			statement.setInt(1, removeItemsOrder.getId().intValue());
			statement.executeUpdate();
		} catch (SQLException sqle) {
			LOGGER.debug(sqle.getStackTrace());
			LOGGER.error(sqle.getMessage());
		}
	}
	
	public BigDecimal calcTotalPrice(List<Long> itemIDs) {
		BigDecimal total = new BigDecimal(0);
		for (Long id: itemIDs) {
			try (Connection connection = databaseConnect(); 
					PreparedStatement statement = connection.prepareStatement("SELECT price FROM items WHERE item_id = ? LIMIT 1");) {
				statement.setInt(1, id.intValue());
				try (ResultSet resultSet = statement.executeQuery();){
					resultSet.next();
					total = total.add(BigDecimal.valueOf(resultSet.getLong(1)));
				} catch (SQLException sqle) {
					LOGGER.debug(sqle.getStackTrace());
					LOGGER.error(sqle.getMessage());
				}
			} catch (SQLException sqle) {
				LOGGER.debug(sqle.getStackTrace());
				LOGGER.error(sqle.getMessage());
			}
		}
		return total;
	}

	
}
