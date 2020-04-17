package persistance.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.domain.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderDAOTest {
	
	List<Long> testList1 = Arrays.asList(1L, 2L, 3L);
	List<Long> testList2 = Arrays.asList(2L, 3L);
	List<Long> testList3 = Arrays.asList(1L, 2L);
	List<Long> testList4 = Arrays.asList(1L, 3L);
	
	@Before
	public void init() {
		DAOConnect.username = "root";
		DAOConnect.password = "TryBreakingThisPassword! Hackers";
		DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
	}
	@Spy
	@InjectMocks
	private OrderDAO orderDAO;
	
	@Test
	public void readAllTest() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, testList1, BigDecimal.valueOf(90.00), LocalDate.of(2020, 04, 17)));
		orders.add(new Order(2L, testList2, BigDecimal.valueOf(70.00), LocalDate.of(2020, 04, 17)));
		orders.add(new Order(3L, testList3, BigDecimal.valueOf(50.00), LocalDate.of(2020, 04, 17)));
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		assertEquals(orders, orderDAO.readAll());
	}
	
	@Test
	public void readTest() {
		Order order = new Order(1L, testList1, BigDecimal.valueOf(90.00), LocalDate.of(2020, 04, 17));
		Mockito.when(orderDAO.read(1L)).thenReturn(order);
		assertEquals(order, orderDAO.read(1L));
	}

	@Test
	public void createTest() {
		Order order = new Order(4L, 2L, testList4, BigDecimal.valueOf(60.00), LocalDate.of(2020, 04, 17));
		Mockito.when(orderDAO.create(order)).thenReturn(order);
		assertEquals(order, orderDAO.create(order));
	}

	@Test
	public void updateTest() {
		Order order = new Order(2L, 2L, testList4, BigDecimal.valueOf(60.00), LocalDate.of(2020, 04, 17));
		Mockito.when(orderDAO.update(order)).thenReturn(order);
		assertEquals(order, orderDAO.update(order));
	}
	
	@Test
	public void deleteTest() {
		orderDAO.delete(1L);
		Mockito.verify(orderDAO, Mockito.times(1)).delete(1L);
	}

}
