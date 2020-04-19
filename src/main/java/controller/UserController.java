package controller;

import java.util.List;

import org.apache.log4j.Logger;

import persistance.domain.User;
import service.CrudService;
import utilities.InputScanner;

public class UserController implements CrudController<User>{
	
	public static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	private CrudService<User> userServices;
	
	public UserController(CrudService<User> userServices) {
		this.userServices = userServices;
	}

	String getInput() {
		return InputScanner.getInput();
	}

	@Override
	public List<User> readAll() {
		List<User> allUsers = userServices.readAll();
		for(User user: allUsers) {
			LOGGER.info(user.toString());
		}
		return allUsers;
	}
	
	@Override
	public User read() {
		LOGGER.info("Please enter the user's ID: ");
		Long customerID = Long.valueOf((getInput()));
		User singleCustomer = userServices.read(customerID);
		LOGGER.info(singleCustomer.toString());
		return singleCustomer;
	}

	@Override
	public User create(Long userID) {
		User tempUser = inputUserData();
		User user = userServices.create(new User(tempUser.getFirstName(), tempUser.getLastName(), tempUser.getUsername(), tempUser.getPassword()));
		LOGGER.info("User created.");
		return user;
	}

	@Override
	public User update(Long userID) {
		LOGGER.info("Please enter the id of the user you would like to update");
		Long id = Long.valueOf(getInput());
		User tempUser = inputUserData();
		User user = userServices.update(new User(id, tempUser.getFirstName(), tempUser.getLastName(), tempUser.getUsername(), tempUser.getPassword()));
		LOGGER.info("User updated");
		return user;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the user you would like to delete");
		Long id = Long.valueOf(getInput());
		userServices.delete(id);
	}
	
	public User inputUserData() {
		LOGGER.info("Please enter the user's first name: ");
		String firstName = getInput();
		LOGGER.info("Please enter the user's last name: ");
		String lastName = getInput();
		LOGGER.info("Please enter the user's username: ");
		String username = getInput();
		LOGGER.info("Please enter the user's password: ");
		String password = getInput();
		return new User(firstName, lastName, username, password);
	}
	

}
