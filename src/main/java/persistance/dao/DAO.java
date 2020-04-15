package persistance.dao;

import java.util.List;

public interface DAO <T> {
	
	T read(Long id);
	
	List<T> readAll();
    
    T create(T t);
     
    T update(T t);
     
    void delete(Long id);

}
