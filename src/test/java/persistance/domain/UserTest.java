package persistance.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	private User other;
	
	@Before
	public void setUp() {
		user = new User(1L, "Luke", "Conway", "Admin", "securePass");
		other = new User(1L, "Luke", "Conway", "Admin", "securePass");
	}
	
	@Test
	public void settersTest() {
		assertNotNull(user.getUserID());
		assertNotNull(user.getFirstName());
		assertNotNull(user.getLastName());
		assertNotNull(user.getUsername());
		
		user.setUserID(null);
		assertNull(user.getUserID());
		user.setFirstName(null);
		assertNull(user.getFirstName());
		user.setLastName(null);
		assertNull(user.getLastName());
		user.setUsername(null);
		assertNull(user.getUsername());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(user.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(user.equals(new Object()));
	}
	
	@Test
	public void createCustomerWithId() {
		assertEquals(1L, user.getUserID(), 0);
		assertEquals("Luke", user.getFirstName());
		assertEquals("Conway", user.getLastName());
		assertEquals("Admin", user.getUsername());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(user.equals(user));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(user.equals(other));
	}
	
	@Test
	public void customerNameNullButOtherNameNotNull() {
		user.setFirstName(null);
		assertFalse(user.equals(other));
	}
	
	@Test
	public void userNamesNotEqual() {
		other.setFirstName("cade");
		assertFalse(user.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		user.setFirstName(null);
		other.setFirstName(null);
		assertTrue(user.equals(other));
	}
	
	@Test
	public void nullUserId() {
		user.setUserID(null);
		assertFalse(user.equals(other));
	}
	
	@Test
	public void nullUserIdOnBoth() {
		user.setUserID(null);
		other.setUserID(null);
		assertTrue(user.equals(other));
	}
	
	@Test
	public void otherUserIdDifferent() {
		other.setUserID(2L);
		assertFalse(user.equals(other));
	}
	
	@Test
	public void nullLastName() {
		user.setLastName(null);
		assertFalse(user.equals(other));
	}
	
	@Test
	public void nullLastNameOnBoth() {
		user.setLastName(null);
		other.setLastName(null);
		assertTrue(user.equals(other));
	}
	
	@Test
	public void otherLastNameDifferent() {
		other.setLastName("thompson");
		assertFalse(user.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		User customer = new User( "Luke", "Conway", "Admin", "securePass");
		assertNull(customer.getUserID());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getLastName());
		assertNotNull(customer.getUsername());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(user.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		User user = new User(null, null, null, null,null);
		User other = new User(null, null, null, null, null);
		assertEquals(user.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "User ID: 1 First Name: Luke Last Name: Conway Username: Admin";
		assertEquals(toString, user.toString());
	}

}
