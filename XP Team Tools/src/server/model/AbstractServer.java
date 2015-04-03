package server.model;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * An abstract server with some main necessary functions still to implement
 * @author alberto
 */
public abstract class AbstractServer {

	protected ServerSocket serverSocket;

	public AbstractServer() {
		super();
	}

	/**
	 * Opens the port
	 * @param port port to open
	 * @throws IOException
	 */
	public void openPort(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	/**
	 * Tests if the port is closed
	 * @return true if the port is closed, false if is not
	 */
	public boolean isPortClosed() {
		return serverSocket.isClosed();
	}

	/**
	 * closes the last socket opened with openPort()
	 * @throws IOException
	 */
	public void closePort() throws IOException {

		serverSocket.close();
	}

	/**
	 * Function to implement in order to listen all the clients (executed for all the life of the server)
	 */
	public abstract void listenClients();
}
