package service;

import java.util.List;

import persistance.dao.DAO;
import persistance.domain.Order;

public class OrderServices implements CrudService<Order>{
	
	private DAO<Order> orderDAO;
	
	public OrderServices(DAO<Order> orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@Override
	public Order read(Long id) {
		return orderDAO.read(id);
	}

	@Override
	public List<Order> readAll() {
		return orderDAO.readAll();
	}

	@Override
	public Order create(Order createOrder) {
		return orderDAO.create(createOrder);
	}

	@Override
	public Order update(Order updateOrder) {
		return orderDAO.update(updateOrder);
	}

	@Override
	public void delete(Long id) {
		orderDAO.delete(id);
	}

}
