package persistance.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {

	@Before
	public void init() {
		DAOConnect.username = "root";
		DAOConnect.password = "TryBreakingThisPassword! Hackers";
		DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
	}
	@Spy
	@InjectMocks
	private CustomerDAO customerDAO;
	
	@Test
	public void readAllTest() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Luke1", "Conway", "30 Test Road", "Test@tester.com", "testing"));
		customers.add(new Customer("Luke2", "Conway", "30 Test Road", "Test@tester.com", "testing"));
		customers.add(new Customer("Luke3", "Conway", "30 Test Road", "Test@tester.com", "testing"));
		customers.add(new Customer("Luke4", "Conway", "30 Test Road", "Test@tester.com", "testing"));
		Mockito.when(customerDAO.readAll()).thenReturn(customers);
		assertEquals(customers, customerDAO.readAll());
	}
	
	@Test
	public void readTest() {
		Customer customer = new Customer("Luke1", "Conway", "30 Test Road", "Test@tester.com", "testing");
		Mockito.when(customerDAO.read(1L)).thenReturn(customer);
		assertEquals(customer, customerDAO.read(1L));
	}

	@Test
	public void createTest() {
		Customer customer = new Customer("Luke1", "Conway", "30 Test Road", "Test@tester.com", "testing");
		Mockito.when(customerDAO.create(customer)).thenReturn(customer);
		assertEquals(customer, customerDAO.create(customer));
	}

	@Test
	public void updateTest() {
		Customer customer = new Customer(2L, "Luke7", "Conway", "30 Test Road", "Test@tester.com", "testing");
		Mockito.when(customerDAO.update(customer)).thenReturn(customer);
		assertEquals(customer, customerDAO.update(customer));
	}
	
	@Test
	public void deleteTest() {
		customerDAO.delete(1L);
		Mockito.verify(customerDAO, Mockito.times(1)).delete(1L);
	}

}
