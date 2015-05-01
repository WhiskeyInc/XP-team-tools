package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

public class ClientConnectionTest {

	@Test
	public void equalsClientDetailTest() {
		
		String nickname = "Alb";
		String teamName = "Prova";
		
		ClientConnectionDetails conDet = new ClientConnectionDetails(nickname, teamName);
		ClientDetails det = new ClientDetails(nickname, teamName);
		assertTrue(conDet.equals(det));
		
	}
	
	@Test
	public void equalsTest() throws Exception {
		String nickname = "Alb";
		String teamName = "Prova";
		
		ClientConnectionDetails conDet = new ClientConnectionDetails(nickname, teamName);
		ClientConnectionDetails det = new ClientConnectionDetails(nickname, "Test");
		
		assertFalse(conDet.equals(det));
	}

}
