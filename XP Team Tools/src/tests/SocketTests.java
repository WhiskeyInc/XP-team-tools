package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.model.Server;

public class SocketTests {

	
	@Test
	public void openPortTest() throws Exception {
		Server server = new Server(null);
		server.openPort(9999);
		assertTrue(!server.isPortClosed());
		// Deve chiudersi, altrimenti per il prossimo test resta aperta
		server.closePort();

	}

	@Test
	public void closePortTest() throws Exception {

		Server server = new Server(null);
		server.openPort(9999);
		server.closePort();
		assertTrue(server.isPortClosed());
	}

}
