package persistance.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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

}
