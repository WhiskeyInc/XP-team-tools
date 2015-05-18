package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

public class ClientConnectionDetailsTest {

	@Test
	public void equalsClientDetailTest() {
		
		String nickname = "Alb";
		String teamName = "Prova";
		
		ClientConnectionDetails conDet = new ClientConnectionDetails(nickname, teamName, null);
		ClientDetails det = new ClientDetails(nickname, teamName, null);
		assertTrue(conDet.equals(det));
		
		det.setPwd("PASSWORD");
		assertTrue(conDet.equals(det));
		
		conDet = new ClientConnectionDetails(nickname, teamName + "b");
		assertTrue(conDet.equals(det));
		
		conDet = new ClientConnectionDetails(nickname + "b", teamName);
		assertFalse(conDet.equals(det));

		
	}
	
	@Test
	public void equalsTest() throws Exception {
		String nickname = "Alb";
		String teamName = "Prova";
		
		ClientConnectionDetails conDet = new ClientConnectionDetails(nickname, teamName, null);
		ClientConnectionDetails det = new ClientConnectionDetails(nickname, "Test", null);
		
		assertTrue(conDet.equals(det));
		
		det = new ClientConnectionDetails(nickname + "F", teamName);
		assertFalse(conDet.equals(det));
		
	}

}
