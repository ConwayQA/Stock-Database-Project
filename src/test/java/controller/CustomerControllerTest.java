package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import persistance.domain.Customer;
import service.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Before
	public void init() {
		DAOConnect.setUsername("root");
		DAOConnect.setPassword("TryBreakingThisPassword! Hackers");
		DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
	}
	
	@Mock
	private CustomerServices customerServices;
	
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void readTest() {
		Customer customer = new Customer("Luke1", "Conway", "30 Test Road", "Test@tester.com", "testing", 1L);
		String id = "1";
		Mockito.doReturn(id).when(customerController).getInput();
		Mockito.when(customerServices.read(1L)).thenReturn(customer);
		assertEquals(customer, customerController.read());
	}
	
	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerServices);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Luke1", "Conway", "30 Test Road", "Test@tester.com", "testing", 1L));
		customers.add(new Customer("Luke2", "Conway", "30 Test Road", "Test@tester.com", "testing", 1L));
		customers.add(new Customer("Luke3", "Conway", "30 Test Road", "Test@tester.com", "testing", 2L));
		Mockito.when(customerServices.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

//	@Test
//	public void createTest() {
//		String firstName = "Luke";
//		String lastName = "Conway";
//		String address = "30 Test Road";
//		String email = "Test@tester.com";
//		String postcode = "testing";
//		Mockito.doReturn(firstName, lastName, address, email, postcode).when(customerController).getInput();
//		Customer customer = new Customer(firstName, lastName, address, email, postcode);
//		Customer savedCustomer = new Customer(1L, "Luke1", "Conway", "30 Test Road", "Test@tester.com", "testing", 1L);
//		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
//		assertEquals(savedCustomer, customerController.create(1L));
//	}

	@Test
	public void updateTest() {
		String id = "1";
		String firstName = "Luke";
		String lastName = "Conway";
		String address = "30 Test Road";
		String email = "Test@tester.com";
		String postcode = "testing";
		Mockito.doReturn(id, firstName, lastName, address, email, postcode).when(customerController).getInput();
		Customer customer = new Customer(1L, firstName, lastName, address, email, postcode, 1L);
		Mockito.when(customerServices.update(customer)).thenReturn(customer);
		assertEquals(customer, customerController.update(1L));
	}
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(customerController).getInput();
		customerController.delete();
		Mockito.verify(customerServices, Mockito.times(1)).delete(1L);
	}
}
