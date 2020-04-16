package persistance.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.mockito.Mockito;

public class DomainTest {
	
	@Test
	public void customerTest() {
		Domain domain = Domain.CUSTOMER;
		assertTrue(domain.getDescription().toLowerCase().contains("store customer"));
	}
	
	@Test
	public void itemTest() {
		Domain domain = Domain.ITEMS;
		assertTrue(domain.getDescription().toLowerCase().contains("stock"));
	}

	@Test
	public void orderTest() {
		Domain domain = Domain.ORDERS;
		assertTrue(domain.getDescription().toLowerCase().contains("purchases"));
	}
	
	@Test
	public void userTest() {
		Domain domain = Domain.USERS;
		assertTrue(domain.getDescription().toLowerCase().contains("staff"));
	}
	
	@Test
	public void stopTest() {
		Domain domain = Domain.END;
		assertTrue(domain.getDescription().toLowerCase().contains("closes"));
	}
	
	@Test
	public void printTest() {
		String toString = "CUSTOMER: Information on store customersITEMS: "
				+ "Details on items in stockORDERS: Collection of customers "
				+ "purchases of itemsUSERS: Information on staff users of the "
				+ "systemEND: Closes the application once you are done";
		assertEquals(toString, Domain.printDomains());
	}
	
	@Test
	public void getDomainTest() {
		String input = "ORDERS";
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    assertEquals(Domain.ORDERS, Domain.getDomain());
	}
}
