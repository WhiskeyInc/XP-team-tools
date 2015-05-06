package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import server.model.ClientsManager2;
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
		ClientsManager2 manager = new ClientsManager2();
		ClientConnectionDetails conDet = new ClientConnectionDetails("Alb", "Test", null);
		conDet.setRealTimeSocket(new Socket("localhost", 9999));
		manager.registerClient(conDet);
		conDet.setRealTimeSocket(new Socket("localhost", 9999));
		
		assertEquals(1, manager.size());
		
		ClientDetails det = new ClientDetails("Alb", "Test", null);
		ClientConnectionDetails conDe2 = manager.get(det);
		
		assertTrue(conDe2.getRealTimeSocket().equals(conDet.getRealTimeSocket()));
		
	}

}
