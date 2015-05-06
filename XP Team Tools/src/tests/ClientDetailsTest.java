package tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import client.model.ClientDetails;

public class ClientDetailsTest {

	@Test
	public void equalsTest() {
		
		ClientDetails det1 = new ClientDetails("Alb", "Prova", null);
		ClientDetails det2 = new ClientDetails("Alb", "Test", null);
		
		assertFalse(det1.equals(det2));
		
		
	}

}
