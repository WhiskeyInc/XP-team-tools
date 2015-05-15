package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.model.ClientsManager2;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

public class ClientsManagerTest {

	private ClientsManager2 clientsManager;
	private ClientConnectionDetails conDet;
	
	@Before
	public void setUp() {
		clientsManager = ClientsManager2.getInstance();
		clientsManager.setTestModeEnabled(true);
		conDet = new ClientConnectionDetails("Alb", "Test", "Alb123");
	}


	@Test
	public void registerTest() throws Exception {
		clientsManager.registerClient(conDet);
		assertEquals(1, clientsManager.size());
		clientsManager.registerClient(conDet);
		clientsManager.registerClient(conDet);
		assertEquals(1, clientsManager.size());
		clientsManager.registerClient(new ClientConnectionDetails("Alb", "DIVERSO"));
		assertEquals(1, clientsManager.size());
		
	}

	@Test
	public void hasTest() throws Exception {
		clientsManager.registerClient(conDet);
		assertTrue(clientsManager.has(new ClientConnectionDetails("Alb", "Test")));
	}
	
	@Test
	public void getTest() throws Exception {
		clientsManager.registerClient(conDet);
		ClientDetails det = new ClientDetails("Alb", "Test");
		ClientConnectionDetails conDet1 = clientsManager.get(det);
		assertEquals(conDet, conDet1);
		assertEquals(null, clientsManager.get(new ClientDetails("Bo", "n")));
	}
	
	@Test
	public void removeTest() throws Exception {
		clientsManager.registerClient(conDet);
		assertEquals(1, clientsManager.size());
		clientsManager.remove(conDet);
		assertEquals(0, clientsManager.size());
		clientsManager.registerClient(conDet);
		clientsManager.remove(new ClientConnectionDetails("Alb", "Test", "Alb123"));
		assertEquals(0, clientsManager.size());

	}
}
