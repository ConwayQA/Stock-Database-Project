package services;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.dao.DAO;
import persistance.domain.Item;
import service.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {
	
	@Mock
	private DAO<Item> itemDAO;
	
	@InjectMocks
	private ItemServices itemServices;
	
	@Test
	public void customerServicesCreate() {
		Item item = new Item("Carcassonne", BigDecimal.valueOf(30.00), "Tile based", 2L, 12L, 30L);
		itemServices.create(item);
		Mockito.verify(itemDAO, Mockito.times(1)).create(item);
	}
	@Test
	public void customerServicesRead() {
		itemServices.read(1L);
		Mockito.verify(itemDAO, Mockito.times(1)).read(1L);
	}
	
	@Test
	public void customerServicesReadAll() {
		itemServices.readAll();
		Mockito.verify(itemDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void customerServicesUpdate() {
		Item item = new Item("Carcassonne", BigDecimal.valueOf(30.00), "Tile based", 2L, 12L, 30L);
		itemServices.update(item);
		Mockito.verify(itemDAO, Mockito.times(1)).update(item);
	}
	
	@Test
	public void customerServicesDelete() {
		itemServices.delete(1L);;
		Mockito.verify(itemDAO, Mockito.times(1)).delete(1L);
	}

}
