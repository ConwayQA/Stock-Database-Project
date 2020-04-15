package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Order;
import service.CrudService;
import utilities.InputScanner;

public class OrderController implements CrudController<Order>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudService<Order> orderServices;
	
	public OrderController(CrudService<Order> orderServices) {
		this.orderServices = orderServices;
	}
	
	String getInput() {
		return InputScanner.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> allOrders = orderServices.readAll();
		for(Order order: allOrders) {
			LOGGER.info(order.toString());
		}
		return allOrders;
	}
	
	public Order read() {
		LOGGER.info("Please enter the order's ID: ");
		Long orderID = Long.getLong(getInput());
		Order singleOrder = orderServices.read(orderID);
		LOGGER.info(singleOrder.toString());
		return singleOrder;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the order's ID: ");
		Long orderID = Long.getLong(getInput());
		LOGGER.info("Please enter the number of items being ordered: ");
		Long numOfItems =  Long.getLong(getInput());
		List<Long> itemIDs = new ArrayList<>();
		for (int i = 1; i <= numOfItems; i++) {
			LOGGER.info("Please enter the ID of item number " + i + ": ");
			itemIDs.add(Long.getLong(getInput()));
		}
		Order order = orderServices.create(new Order(orderID, itemIDs));
		LOGGER.info("Order created.");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter the customer's ID: ");
		Long customerID = Long.getLong(getInput());
		LOGGER.info("Please enter the number of items being ordered: ");
		Long numOfItems =  Long.getLong(getInput());
		List<Long> itemIDs = new ArrayList<>();
		for (int i = 1; i <= numOfItems; i++) {
			LOGGER.info("Please enter the ID of item number " + i + ": ");
			itemIDs.add(Long.getLong(getInput()));
		}
		Order order = orderServices.create(new Order(id, customerID, itemIDs));
		LOGGER.info("Order created.");
		return order;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderServices.delete(id);
	}
}