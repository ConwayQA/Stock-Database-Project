package persistance.dao;

import java.util.List;

public interface DAO <T> {
	
	List<T> readAll();
    
    T create(T t);
     
    T update(T t);
     
    void delete(long id);

}
