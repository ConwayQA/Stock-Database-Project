package service;

import java.util.List;

import persistance.dao.DAO;
import persistance.domain.User;

public class UserServices implements CrudService<User>{
	
	private DAO<User> userDAO;
	
	public UserServices(DAO<User> userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public User read(Long id) {
		return userDAO.read(id);
	}
	
	@Override
	public List<User> readAll() {
		return userDAO.readAll();
	}

	@Override
	public User create(User createUser) {
		return userDAO.create(createUser);
	}

	@Override
	public User update(User updateUser) {
		return userDAO.update(updateUser);
	}

	@Override
	public void delete(Long id) {
		userDAO.delete(id);
	}

}
