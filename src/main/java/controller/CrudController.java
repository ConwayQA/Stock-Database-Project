package controller;

import java.util.List;

public interface CrudController<T> {
	
	List<T> readAll();
	
	T create();
	
	T update();
	
	void delete();
	
}
