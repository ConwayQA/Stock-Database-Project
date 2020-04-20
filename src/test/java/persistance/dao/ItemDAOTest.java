package persistance.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import persistance.domain.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemDAOTest {
	
	@Before
	public void init() {
		DAOConnect.username = "root";
		DAOConnect.password = "TryBreakingThisPassword! Hackers";
		DAOConnect.init("jdbc:mysql://34.76.51.174:3306/ims", "root", "TryBreakingThisPassword! Hackers", "src/test/resources/sql-schema.sql");
	}
	@Spy
	@InjectMocks
	private ItemDAO itemDAO;
	
	@Test
	public void readAllTest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("Carcassonne", BigDecimal.valueOf(20.00), "Tile Based", 2L, 12L, 30L, 1L));
		items.add(new Item("Settlers of Catan", BigDecimal.valueOf(30.00), "Resource Management", 3L, 6L, 30L, 1L));
		items.add(new Item("Pandemic", BigDecimal.valueOf(40.00), "Players vs Game", 1L, 6L, 60L, 1L));
		Mockito.when(itemDAO.readAll()).thenReturn(items);
		assertEquals(items, itemDAO.readAll());
	}
	
	@Test
	public void readTest() {
		Item item = new Item("Carcassonne", BigDecimal.valueOf(20.00), "Tile Based", 2L, 12L, 30L, 1L);
		Mockito.when(itemDAO.read(1L)).thenReturn(item);
		assertEquals(item, itemDAO.read(1L));
	}

	@Test
	public void createTest() {
		Item item = new Item("Carcassonne", BigDecimal.valueOf(20.00), "Tile Based", 2L, 12L, 30L, 1L);
		Mockito.when(itemDAO.create(item)).thenReturn(item);
		assertEquals(item, itemDAO.create(item));
	}

	@Test
	public void updateTest() {
		Item item = new Item(2L, "Carcassonne", BigDecimal.valueOf(20.00), "Tile Based", 2L, 12L, 30L, 1L);
		Mockito.when(itemDAO.update(item)).thenReturn(item);
		assertEquals(item, itemDAO.update(item));
	}
	
	@Test
	public void deleteTest() {
		itemDAO.delete(1L);
		Mockito.verify(itemDAO, Mockito.times(1)).delete(1L);
	}
}
