package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import persistance.domain.Customer;
import service.CustomerServices;

public class CustomerControllerTest {

	/**
	 *  The thing I want to fake functionlity for
	 */
	@Mock
	private CustomerServices customerServices;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerServices);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Luke", "Conway", "30 Test Road", "Test@tester.com", "testing"));
		customers.add(new Customer("Cade", "Jackson", "37 Test lane", "Testing@tester.com", "tsttst"));
		customers.add(new Customer("Elle", "White", "25 Test street", "Tst@tstr.co.uk", "teeeeeest"));
		Mockito.when(customerServices.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

	@Test
	public void createTest() {
		String firstName = "Luke";
		String lastName = "Conway";
		String address = "30 Test Road";
		String email = "Test@tester.com";
		String postcode = "testing";
		Mockito.doReturn(firstName, lastName, address, email, postcode).when(customerController).getInput();
		Customer customer = new Customer(firstName, lastName, address, email, postcode);
		Customer savedCustomer = new Customer(1L, "Luke", "Conway", "30 Test Road", "Test@tester.com", "testing");
		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
		assertEquals(savedCustomer, customerController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		String id = "1";
		String firstName = "Luke";
		String lastName = "Conway";
		String address = "30 Test Road";
		String email = "Test@tester.com";
		String postcode = "testing";
		Mockito.doReturn(id, firstName, lastName, address, email, postcode).when(customerController).getInput();
		Customer customer = new Customer(1L, firstName, lastName, address, email, postcode);
		Mockito.when(customerServices.update(customer)).thenReturn(customer);
		assertEquals(customer, customerController.update());
	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(customerController).getInput();
		customerController.delete();
		Mockito.verify(customerServices, Mockito.times(1)).delete(1L);
	}
}
