package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import persistance.domain.Domain;

public class ControllerActionsTest {

	@Test
	public void createTest() {
		ControllerActions domain = ControllerActions.CREATE;
		assertTrue(domain.getDescription().toLowerCase().contains("save"));
	}
	
	@Test
	public void readTest() {
		ControllerActions domain = ControllerActions.READ;
		assertTrue(domain.getDescription().toLowerCase().contains("read a record"));
	}

	@Test
	public void readallTest() {
		ControllerActions domain = ControllerActions.READALL;
		assertTrue(domain.getDescription().toLowerCase().contains("read all records"));
	}
	
	@Test
	public void updateTest() {
		ControllerActions domain = ControllerActions.UPDATE;
		assertTrue(domain.getDescription().toLowerCase().contains("update"));
	}
	
	@Test
	public void deleteTest() {
		ControllerActions domain = ControllerActions.DELETE;
		assertTrue(domain.getDescription().toLowerCase().contains("remove"));
	}
	
	@Test
	public void returnTest() {
		ControllerActions domain = ControllerActions.RETURN;
		assertTrue(domain.getDescription().toLowerCase().contains("return"));
	}
	
	@Test
	public void printTest() {
		String toString = "CREATE: To save a new record to the database"
							+ "READ: To read a record from the database"
							+ "READALL: To read all records from the database"
							+ "UPDATE: To update a record on the database with new information"
							+ "DELETE: To remove a record from the database"
							+ "RETURN: To return to table selection";
		assertEquals(toString, ControllerActions.printActions());
	}
	
	@Test
	public void getControllerActionsTest() {
		String input = "CREATE";
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    assertEquals(ControllerActions.CREATE, ControllerActions.getControllerAction());
	}
}
