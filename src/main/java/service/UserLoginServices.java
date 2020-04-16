package service;

import persistance.dao.LoginDAO;
import persistance.domain.User;

public class UserLoginServices implements LoginService<User>{
	
	private LoginDAO<User> userLogin;
	public UserLoginServices(LoginDAO<User> userLogin) {
		this.userLogin = userLogin;
	}
	
	@Override
	public User login(String username, String password) {
		userLogin.login(username, password);
		return null;
	}
}
