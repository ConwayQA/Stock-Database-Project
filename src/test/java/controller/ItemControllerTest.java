package controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.dao.DAOConnect;
import persistance.domain.Item;
import service.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Before
	public void init() {
		DAOConnect.username = "root";
		DAOConnect.password = "TryBreakingThisPassword! Hackers";
		DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
	}

	@Mock
	private ItemServices itemService;
	
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readTest() {
		Item item = new Item("Carcassonne", BigDecimal.valueOf(20.00), "Tile based", 2L, 12L, 30L);
		String id = "1";
		Mockito.doReturn(id).when(itemController).getInput();
		Mockito.when(itemService.read(1L)).thenReturn(item);
		assertEquals(item, itemController.read());
	}
	
	@Test
	public void readAllTest() {
		ItemController itemController1 = new ItemController(itemService);
		List<Item> items = new ArrayList<>();
		items.add(new Item("Carcassonne", BigDecimal.valueOf(20.00), "Tile based", 2L, 12L, 30L));
		items.add(new Item("Settlers of Catan", BigDecimal.valueOf(30.00), "resource management", 3L, 5L, 60L));
		items.add(new Item("Pandemic", BigDecimal.valueOf(40.00), "Players vs game", 1L, 6L, 60L));
		Mockito.when(itemService.readAll()).thenReturn(items);
		assertEquals(items, itemController1.readAll());
	}

	@Test
	public void createTest() {
		String name = "Carcassonne";
		String price = "20.00";
		String genre = "Tile based";
		String minPlayers = "2";
		String maxPlayers = "12";
		String avgPlayTime = "30";
		Mockito.doReturn(name, price, genre, minPlayers, maxPlayers, avgPlayTime).when(itemController).getInput();
		Item item = new Item(name, BigDecimal.valueOf(Double.parseDouble(price)), genre, Long.parseLong(minPlayers), 
								Long.parseLong(maxPlayers), Long.parseLong(avgPlayTime));
		Item savedItem = new Item(1L, "Carcassonne", BigDecimal.valueOf(20.00), "Tile based", 2L, 12L, 30L);
		Mockito.when(itemService.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}
	
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String name = "Settlers of Catan - star wars";
//		String price = "40.00";
//		String genre = "resource Management";
//		String minPlayers = "2";
//		String maxPlayers = "12";
//		String avgPlayTime = "30";
//		Mockito.doReturn(id, name, price, genre, minPlayers, maxPlayers, avgPlayTime).when(itemController).getInput();
//		Item item = new Item(Long.parseLong(id), name, BigDecimal.valueOf(Double.parseDouble(price)), genre, 
//								Long.parseLong(minPlayers), Long.parseLong(maxPlayers), Long.parseLong(avgPlayTime));
//		Mockito.when(itemService.update(item)).thenReturn(item);
//		assertEquals(item, itemController.update());
//	}
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemService, Mockito.times(1)).delete(1L);
	}
}
