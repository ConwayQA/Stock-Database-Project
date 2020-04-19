package controller;

import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.Customer;
import service.CrudService;
import utilities.InputScanner;

public class CustomerController implements CrudController<Customer>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudService<Customer> customerServices;
	
	public CustomerController(CrudService<Customer> customerServices) {
		this.customerServices = customerServices;
	}
	
	String getInput() {
		return InputScanner.getInput();
	}

	@Override
	public List<Customer> readAll() {
		List<Customer> allCustomers = customerServices.readAll();
		for(Customer customer: allCustomers) {
			LOGGER.info(customer.toString());
		}
		return allCustomers;
	}
	
	@Override
	public Customer read() {
		LOGGER.info("Please enter the customer's ID: ");
		Long customerID = Long.valueOf((getInput()));
		Customer singleCustomer = customerServices.read(customerID);
		LOGGER.info(singleCustomer.toString());
		return singleCustomer;
	}

	@Override
	public Customer create(Long userID) {
		Customer tempCustomer = inputCustomerData();
		Customer customer = customerServices.create(new Customer(tempCustomer.getFirstName(), tempCustomer.getLastName(),
														tempCustomer.getAddress(), tempCustomer.getEmail(), tempCustomer.getPostcode(), userID));
		LOGGER.info("Customer created.");
		return customer;
	}

	@Override
	public Customer update(Long userID) {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = Long.valueOf(getInput());
		Customer tempCustomer = inputCustomerData();
		Customer customer = customerServices.update(new Customer(id, tempCustomer.getFirstName(), tempCustomer.getLastName(),
														tempCustomer.getAddress(), tempCustomer.getEmail(), tempCustomer.getPostcode(), userID));
		LOGGER.info("Customer updated");
		return customer;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = Long.valueOf(getInput());
		customerServices.delete(id);
	}
	
	public Customer inputCustomerData() {
		LOGGER.info("Please enter the customer's first name: ");
		String firstName = getInput();
		LOGGER.info("Please enter the customer's last name: ");
		String lastName = getInput();
		LOGGER.info("Please enter the customer's address: ");
		String address = getInput();
		LOGGER.info("Please enter the customer's email: ");
		String email = getInput();
		LOGGER.info("Please enter the customer's postcode: ");
		String postcode = getInput();
		return new Customer(firstName, lastName, address, email, postcode);
	}


}
