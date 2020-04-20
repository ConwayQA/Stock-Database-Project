package controller;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.dao.DAOConnect;
import persistance.domain.Order;
import service.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Before
	public void init() {
		DAOConnect.username = "root";
		DAOConnect.password = "TryBreakingThisPassword! Hackers";
		DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
	}
	
	@Mock
	private OrderServices orderServices;
	
	@Spy
	@InjectMocks
	private OrderController orderController;

	List<Long> testList = Arrays.asList(3L, 2L, 5L);
	
	@Test
	public void readTest() {
		Order order = new Order(1L, 1L, testList, BigDecimal.valueOf(0.00), LocalDate.now(), 1L);
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		Mockito.when(orderServices.read(1L)).thenReturn(order);
		assertEquals(order, orderController.read());
	}
	
	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, testList, BigDecimal.valueOf(0.00), LocalDate.now(), 1L));
		orders.add(new Order(2L, 2L, testList, BigDecimal.valueOf(0.00), LocalDate.now(), 2L));
		orders.add(new Order(3L, 3L, testList, BigDecimal.valueOf(0.00), LocalDate.now(), 1L));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

//	@Test
//	public void createTest() {
//		String customerID = "1";
//		String itemNumber = "3";
//		String itemID1 = "1";
//		String itemID2 = "2";
//		String itemID3 = "3";
//		Mockito.doReturn(customerID, itemNumber, itemID1, itemID2, itemID3).when(orderController).getInput();
//		Order order = new Order(1L, testList, BigDecimal.valueOf(0.0), LocalDate.now(), 1L);
//		Order savedOrder = new Order(1L, testList, BigDecimal.valueOf(0.0), LocalDate.now(), 1L);
//		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
//		assertEquals(savedOrder, orderController.create(1L));
//	}
//
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String customerID = "1";
//		String itemNumber = "3";
//		String itemID1 = "3";
//		String itemID2 = "2";
//		String itemID3 = "5";
//		Mockito.doReturn(id, customerID, itemNumber, itemID1, itemID2, itemID3).when(orderController).getInput();
//		Order order = new Order(1L, Long.parseLong(customerID), testList, BigDecimal.valueOf(0.0), LocalDate.now(), 1L);
//		Mockito.when(orderServices.update(order)).thenReturn(order);
//		assertEquals(order, orderController.update(1L));
//	}
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}
}
