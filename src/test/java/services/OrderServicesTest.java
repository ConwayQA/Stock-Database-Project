package services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.dao.DAO;
import persistance.domain.Order;
import service.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesTest {
	
	List<Long> testList = Arrays.asList(1L, 4L, 3L);
	
	@Mock
	private DAO<Order> orderDAO;
	
	@InjectMocks
	private OrderServices itemServices;
	
	@Test
	public void customerServicesCreate() {
		Order order = new Order(1L, testList, BigDecimal.valueOf(30.00), LocalDate.of(2015, 12, 31), 1L);
		itemServices.create(order);
		Mockito.verify(orderDAO, Mockito.times(1)).create(order);
	}
	@Test
	public void customerServicesRead() {
		itemServices.read(1L);
		Mockito.verify(orderDAO, Mockito.times(1)).read(1L);
	}
	
	@Test
	public void customerServicesReadAll() {
		itemServices.readAll();
		Mockito.verify(orderDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void customerServicesUpdate() {
		Order order = new Order(1L, testList, BigDecimal.valueOf(30.00), LocalDate.of(2015, 12, 31), 1L);
		itemServices.update(order);
		Mockito.verify(orderDAO, Mockito.times(1)).update(order);
	}
	
	@Test
	public void customerServicesDelete() {
		itemServices.delete(1L);;
		Mockito.verify(orderDAO, Mockito.times(1)).delete(1L);
	}

}
