package controller;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.domain.Order;
import service.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderServices orderServices;
	
	@Spy
	@InjectMocks
	private OrderController orderController;

	List<Long> testList = Arrays.asList(3L, 2L, 5L);
	
	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, testList, BigDecimal.valueOf(0.00), LocalDate.now()));
		orders.add(new Order(2L, 2L, testList, BigDecimal.valueOf(0.00), LocalDate.now()));
		orders.add(new Order(3L, 3L, testList, BigDecimal.valueOf(0.00), LocalDate.now()));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

	@Test
	public void createTest() {
		String customerID = "1";
		String itemNumber = "3";
		String itemID1 = "1";
		String itemID2 = "2";
		String itemID3 = "3";
		Mockito.doReturn(customerID, itemNumber, itemID1, itemID2, itemID3).when(orderController).getInput();
		Order order = new Order(Long.parseLong(customerID), testList, BigDecimal.valueOf(0.0), LocalDate.now());
		Order savedOrder = new Order(1L, testList, BigDecimal.valueOf(0.0), LocalDate.now());
		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, orderController.create());
	}

	@Test
	public void updateTest() {
		String id = "1";
		String customerID = "1";
		String itemNumber = "3";
		String itemID1 = "3";
		String itemID2 = "2";
		String itemID3 = "5";
		Mockito.doReturn(id, customerID, itemNumber, itemID1, itemID2, itemID3).when(orderController).getInput();
		Order order = new Order(1L, Long.parseLong(customerID), testList, BigDecimal.valueOf(0.0), LocalDate.now());
		Mockito.when(orderServices.update(order)).thenReturn(order);
		assertEquals(order, orderController.update());
	}
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}
}
