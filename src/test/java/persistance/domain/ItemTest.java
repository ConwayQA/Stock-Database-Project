package persistance.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1L, "Carcassonne", BigDecimal.valueOf(30.00), "Tile based", 2L, 12L, 30L);
		other = new Item(1L, "Carcassonne", BigDecimal.valueOf(30.00), "Tile based", 2L, 12L, 30L);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getGenre());
		assertNotNull(item.getPrice());
		assertNotNull(item.getMinPlayers());
		assertNotNull(item.getMaxPlayers());
		assertNotNull(item.getAvgPlayTime());
		
		item.setId(null);
		assertNull(item.getId());
		item.setName(null);
		assertNull(item.getName());
		item.setGenre(null);
		assertNull(item.getGenre());
		item.setPrice(null);
		assertNull(item.getPrice());
		item.setMinPlayers(null);
		assertNull(item.getMinPlayers());
		item.setMaxPlayers(null);
		assertNull(item.getMaxPlayers());
		item.setAvgPlayTime(null);
		assertNull(item.getAvgPlayTime());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("Carcassonne", item.getName());
		assertEquals(BigDecimal.valueOf(30.00), item.getPrice());
		assertEquals("Tile based", item.getGenre());
		assertEquals(2L, item.getMinPlayers(), 0);
		assertEquals(12L, item.getMaxPlayers(), 0);
		assertEquals(30L, item.getAvgPlayTime(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}
	
	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setName(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void itemNamesNotEqual() {
		other.setName("cade");
		assertFalse(item.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setName(null);
		other.setName(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void nullId() {
		item.setId(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		item.setId(null);
		other.setId(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullGenre() {
		item.setGenre(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullGenreOnBoth() {
		item.setGenre(null);
		other.setGenre(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherGenreDifferent() {
		other.setGenre("co-op");
		assertFalse(item.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Item customer = new Item("Carcassonne", BigDecimal.valueOf(30.00), "Tile based", 2L, 12L, 30L);
		assertNull(customer.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getGenre());
		assertNotNull(item.getPrice());
		assertNotNull(item.getMinPlayers());
		assertNotNull(item.getMaxPlayers());
		assertNotNull(item.getAvgPlayTime());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Item customer = new Item(null, null, null, null, null, null, null);
		Item other = new Item(null, null, null, null, null, null, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "ID: 1 Name: Carcassonne price: £30.0 genre: Tile based minimum number of players: 2 maximum number of players: 12 average play time: 30";
		assertEquals(toString, item.toString());
	}
}
