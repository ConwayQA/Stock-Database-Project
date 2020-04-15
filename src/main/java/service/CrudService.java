package service;

import java.util.List;

public interface CrudService<T> {
	
	T read(Long id);
	
	public List<T> readAll();
	
	T create(T t);
	
	T update(T t);
	
	void delete(Long id);

}
