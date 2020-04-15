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
	public Customer create() {
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
		Customer customer = customerServices.create(new Customer(firstName, lastName, address, email, postcode));
		LOGGER.info("Customer created.");
		return customer;
	}

	@Override
	public Customer update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = Long.valueOf(getInput());
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
		Customer customer = customerServices.update(new Customer(id, firstName, lastName, address, email, postcode));
		LOGGER.info("Customer updated");
		return customer;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = Long.valueOf(getInput());
		customerServices.delete(id);
	}


}
