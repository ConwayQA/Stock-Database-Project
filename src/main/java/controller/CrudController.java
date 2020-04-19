package controller;

import java.util.List;

public interface CrudController<T> {
	
	T read();
	
	List<T> readAll();
	
	T create(Long userID);
	
	T update(Long userID);
	
	void delete();
	
}
