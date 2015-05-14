package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.model.ClientsManager2O;
import client.model.ClientConnectionDetails;

public class ClientsManagerTest {


	@Test
	public void containsTest() throws Exception {
		ClientsManager2O manager = new ClientsManager2O();
		ClientConnectionDetails conDet = new ClientConnectionDetails("Alb", "Test");
		manager.registerClient(conDet);
		assertTrue(manager.contains(new ClientConnectionDetails("Alb", "Test")));

	}
}
