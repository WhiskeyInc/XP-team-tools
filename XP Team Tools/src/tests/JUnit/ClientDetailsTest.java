package tests.JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import client.model.ClientDetails;

public class ClientDetailsTest {

	@Test
	public void equalsTest() {
		
		ClientDetails det1 = new ClientDetails("Alb", "Prova", null);
		ClientDetails det2 = new ClientDetails("Alb", "Test", null);
		
		assertTrue(det1.equals(det2));
		
		ClientDetails det3 = new ClientDetails("Alb1", "Test1", null);

		assertFalse(det1.equals(det3));
		det3 = new ClientDetails("Alb1", "Test", null);
		assertFalse(det2.equals(det3));
		
	}

}
