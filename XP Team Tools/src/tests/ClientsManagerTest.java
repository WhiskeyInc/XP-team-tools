package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import server.model.ClientsManager2O;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

public class ClientsManagerTest {

	/**
	 * Server must run
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	@Test
	public void clientManagerTest() throws UnknownHostException, IOException {
		ClientsManager2O manager = new ClientsManager2O();
		ClientConnectionDetails conDet = new ClientConnectionDetails("Alb", "Test");
		conDet.setRealTimeSocket(new Socket("localhost", 9999));
		manager.registerClient(conDet);
		conDet.setRealTimeSocket(new Socket("localhost", 9999));
		
		assertEquals(1, manager.size());
		
		ClientDetails det = new ClientDetails("Alb", "Test");
		ClientConnectionDetails conDe2 = manager.get(det);
		
		assertTrue(conDe2.getRealTimeSocket().equals(conDet.getRealTimeSocket()));
		
	}

}
