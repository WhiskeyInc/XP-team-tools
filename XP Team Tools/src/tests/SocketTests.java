package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.model.ServerStrategy1_1;

public class SocketTests {

	
	@Test
	public void openPortTest() throws Exception {
		ServerStrategy1_1 server = new ServerStrategy1_1(null);
		server.openPort(9999);
		assertTrue(!server.isPortClosed());
		// Deve chiudersi, altrimenti per il prossimo test resta aperta
		server.closePort();

	}

	@Test
	public void closePortTest() throws Exception {

		ServerStrategy1_1 server = new ServerStrategy1_1(null);
		server.openPort(9999);
		server.closePort();
		assertTrue(server.isPortClosed());
	}

}
