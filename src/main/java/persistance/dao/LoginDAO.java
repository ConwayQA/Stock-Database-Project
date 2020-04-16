package persistance.dao;

public interface LoginDAO<T> {
	
	T login(String username, String password);

}
