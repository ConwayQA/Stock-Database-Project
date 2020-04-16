package persistance.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	private Customer other;
	
	@Before
	public void setUp() {
		customer = new Customer(1L, "Luke", "Conway", "30 Test Road", "Test@tester.com", "testing");
		other = new Customer(1L, "Luke", "Conway", "30 Test Road", "Test@tester.com", "testing");
	}
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getLastName());
		assertNotNull(customer.getAddress());
		assertNotNull(customer.getEmail());
		assertNotNull(customer.getPostcode());
		
		customer.setId(null);
		assertNull(customer.getId());
		customer.setFirstName(null);
		assertNull(customer.getFirstName());
		customer.setLastName(null);
		assertNull(customer.getLastName());
		customer.setAddress(null);
		assertNull(customer.getAddress());
		customer.setEmail(null);
		assertNull(customer.getEmail());
		customer.setPostcode(null);
		assertNull(customer.getPostcode());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(customer.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(customer.equals(new Object()));
	}
	
	@Test
	public void createCustomerWithId() {
		assertEquals(1L, customer.getId(), 0);
		assertEquals("Luke", customer.getFirstName());
		assertEquals("Conway", customer.getLastName());
		assertEquals("30 Test Road", customer.getAddress());
		assertEquals("Test@tester.com", customer.getEmail());
		assertEquals("testing", customer.getPostcode());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(customer.equals(customer));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void customerNameNullButOtherNameNotNull() {
		customer.setFirstName(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void customerNamesNotEqual() {
		other.setFirstName("cade");
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		customer.setFirstName(null);
		other.setFirstName(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void nullId() {
		customer.setId(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		customer.setId(null);
		other.setId(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullLastName() {
		customer.setLastName(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullLastNameOnBoth() {
		customer.setLastName(null);
		other.setLastName(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void otherLastNameDifferent() {
		other.setLastName("thompson");
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Customer customer = new Customer( "Luke", "Conway", "30 Test Road", "Test@tester.com", "testing");
		assertNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getLastName());
		assertNotNull(customer.getAddress());
		assertNotNull(customer.getEmail());
		assertNotNull(customer.getPostcode());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(customer.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Customer customer = new Customer(null, null, null, null, null, null);
		Customer other = new Customer(null, null, null, null, null, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "ID: 1 First Name: Luke Last Name: Conway Address: 30 Test Road Email: Test@tester.com Postcode: testing";
		assertEquals(toString, customer.toString());
	}

}
