package persistance.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	private Order order;
	private Order other;
	List<Long> testList = Arrays.asList(1L, 4L, 3L);
	List<Long> testList2 = Arrays.asList(6L, 5L, 5L);
	
	@Before
	public void setUp() {
		
		order = new Order(1L, 1L, testList, BigDecimal.valueOf(30.00), LocalDate.of(2015, 12, 31), 1L);
		other = new Order(1L, 1L, testList, BigDecimal.valueOf(30.00), LocalDate.of(2015, 12, 31), 1L);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomerID());
		assertNotNull(order.getItemIDs());
		assertNotNull(order.getTotalPrice());
		assertNotNull(order.getDate());
		
		order.setId(null);
		assertNull(order.getId());
		order.setCustomerID(null);
		assertNull(order.getCustomerID());
		order.setItemIDs(null);
		assertNull(order.getItemIDs());
		order.setTotalPrice(null);
		assertNull(order.getTotalPrice());
		order.setDate(null);
		assertNull(order.getDate());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(1L, order.getId(), 0);
		assertEquals(1L, order.getCustomerID(), 0);
		assertEquals(testList, order.getItemIDs());
		assertEquals(BigDecimal.valueOf(30.00), order.getTotalPrice());
		assertEquals(LocalDate.of(2015, 12, 31), order.getDate());
	}
	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}
	
	@Test
	public void customerIDNullButOtherNameNotNull() {
		order.setCustomerID(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void customerIDsNotEqual() {
		other.setCustomerID(5L);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullCustomerID() {
		order.setCustomerID(null);
		other.setCustomerID(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void nullId() {
		order.setId(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		order.setId(null);
		other.setId(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullTotalPrice() {
		order.setTotalPrice(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullTotalPriceOnBoth() {
		order.setTotalPrice(null);
		other.setTotalPrice(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherTotalPriceDifferent() {
		other.setTotalPrice(BigDecimal.valueOf(0.0));
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullDate() {
		order.setDate(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullDateOnBoth() {
		order.setDate(null);
		other.setDate(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherDateDifferent() {
		other.setDate(LocalDate.of(2015, 12, 22));
		assertFalse(order.equals(other));
	}
	
	@Test
	public void emptyItemIDS() {
		order.setItemIDs(new ArrayList<>());
		assertFalse(order.equals(other));
	}
	
	@Test
	public void emptyItemIDsOnBoth() {
		order.setItemIDs(new ArrayList<>());
		other.setItemIDs(new ArrayList<>());
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherItemIDsDifferent() {
		other.setItemIDs(testList2);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Order customer = new Order(1L, testList, BigDecimal.valueOf(30.00), LocalDate.of(2015, 12, 31), 1L);
		assertNull(customer.getId());
		assertNotNull(customer.getCustomerID());
		assertNotNull(customer.getItemIDs());
		assertNotNull(customer.getTotalPrice());
		assertNotNull(customer.getDate());
	}
	
	@Test
	public void constructorWithoutPriceAndDate() {
		Order customer = new Order(1L, 1L, testList, 1L);
		assertNotNull(customer.getId());
		assertNotNull(customer.getCustomerID());
		assertNotNull(customer.getItemIDs());
		assertNull(customer.getTotalPrice());
		assertNull(customer.getDate());
	}
	
	@Test
	public void constructorWithoutIdPriceAndDate() {
		Order customer = new Order(1L, testList, 1L);
		assertNull(customer.getId());
		assertNotNull(customer.getCustomerID());
		assertNotNull(customer.getItemIDs());
		assertNull(customer.getTotalPrice());
		assertNull(customer.getDate());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Order customer = new Order(null, null, null, null, null, null);
		Order other = new Order(null, null, null, null, null, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "Order ID: 1 Customer ID: 1 Total Price: £30.0 Date Ordered: 2015-12-31 userID: 1";
		assertEquals(toString, order.toString());
	}

}
