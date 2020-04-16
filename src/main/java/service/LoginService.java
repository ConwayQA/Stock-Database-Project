package service;

public interface LoginService<T> {

	T login(String username, String password);
	
}
