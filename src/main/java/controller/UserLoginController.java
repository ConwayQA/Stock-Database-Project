package controller;

import org.apache.log4j.Logger;

import persistance.domain.User;
import service.LoginService;
import utilities.InputScanner;

public class UserLoginController implements LoginController<User>{
	
	public static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	private LoginService<User> userLogin;
	public UserLoginController(LoginService<User> userLogin) {
		this.userLogin = userLogin;
	}
	
	@Override
	public User login() {
		LOGGER.info("Enter Username: ");
		String username = InputScanner.getInput();
		LOGGER.info("Enter Password: ");
		String password = InputScanner.getInput();
		
		User user = userLogin.login(username, password);
		if (user.isLoggedIn()) {
			LOGGER.info("Login Successful");
			return user;
		}
		return null;
	}
	
}
