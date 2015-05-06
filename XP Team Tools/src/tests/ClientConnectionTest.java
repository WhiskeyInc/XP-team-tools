package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

public class ClientConnectionTest {

	@Test
	public void equalsClientDetailTest() {
		
		String nickname = "Alb";
		String teamName = "Prova";
		
		ClientConnectionDetails conDet = new ClientConnectionDetails(nickname, teamName, null);
		ClientDetails det = new ClientDetails(nickname, teamName, null);
		assertTrue(conDet.equals(det));
		
	}
	
	@Test
	public void equalsTest() throws Exception {
		String nickname = "Alb";
		String teamName = "Prova";
		
		ClientConnectionDetails conDet = new ClientConnectionDetails(nickname, teamName, null);
		ClientConnectionDetails det = new ClientConnectionDetails(nickname, "Test", null);
		
		assertFalse(conDet.equals(det));
	}

}
