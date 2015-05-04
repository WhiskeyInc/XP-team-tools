package tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import client.model.ClientDetails;

public class ClientDetailsTest {

	@Test
	public void equalsTest() {
		
		ClientDetails det1 = new ClientDetails("Alb", "Prova");
		ClientDetails det2 = new ClientDetails("Alb", "Test");
		
		assertFalse(det1.equals(det2));
		
		
	}

}
