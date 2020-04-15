package service;

import java.util.List;

import persistance.dao.DAO;
import persistance.domain.Customer;
import persistance.domain.Order;

public class CustomerServices implements CrudService<Customer>{
	
	private DAO<Customer> customerDAO;
	
	public CustomerServices(DAO<Customer> customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	@Override
	public Customer read(Long id) {
		return customerDAO.read(id);
	}
	
	@Override
	public List<Customer> readAll() {
		return customerDAO.readAll();
	}

	@Override
	public Customer create(Customer createCustomer) {
		return customerDAO.create(createCustomer);
	}

	@Override
	public Customer update(Customer updateCustomer) {
		return customerDAO.update(updateCustomer);
	}

	@Override
	public void delete(Long id) {
		customerDAO.delete(id);
	}
	

}
