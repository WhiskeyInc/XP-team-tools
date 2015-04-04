package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.model.AbstractServer;
import server.model.TestableServerRecover;

public class SocketTests {

	
	@Test
	public void openPortTest() throws Exception {
		AbstractServer server = new TestableServerRecover();

		server.openPort(9999);
		assertTrue(!server.isPortClosed());
		// Deve chiudersi, altrimenti per il prossimo test resta aperta
		server.closePort();

	}

	@Test
	public void closePortTest() throws Exception {

		AbstractServer server = new TestableServerRecover();
		server.openPort(9999);
		server.closePort();
		assertTrue(server.isPortClosed());
	}

}
