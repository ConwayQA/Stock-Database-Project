package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.dao.DAO;
import persistance.domain.Customer;
import service.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTest {

	@Mock
	private DAO<Customer> customerDAO;
	
	@InjectMocks
	private CustomerServices customerServices;
	
	@Test
	public void customerServicesCreate() {
		Customer customer = new Customer("Luke", "Conway", "30 Test Road", "Test@tester.com", "testing");
		customerServices.create(customer);
		Mockito.verify(customerDAO, Mockito.times(1)).create(customer);
	}
	
//	@Test
//	public void customerServicesRead() {
//		customerServices.readAll();
//		Mockito.verify(customerDao, Mockito.times(1)).readAll();
//	}
	
//	@Test
//	public void customerServicesUpdate() {
//		Customer customer = new Customer("chris", "perrins");
//		customerServices.update(customer);
//		Mockito.verify(customerDao, Mockito.times(1)).update(customer);
//	}
	
//	@Test
//	public void customerServicesDelete() {
//		customerServices.delete(1L);;
//		Mockito.verify(customerDao, Mockito.times(1)).delete(1L);
//	}
}
