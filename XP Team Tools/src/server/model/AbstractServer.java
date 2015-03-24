package server.model;

import java.io.IOException;
import java.net.ServerSocket;

public abstract class AbstractServer {

	protected ServerSocket serverSocket;

	public AbstractServer() {
		super();
	}

	public void openPort(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public boolean isPortClosed() {
		return serverSocket.isClosed();
	}

	public void closePort() throws IOException {

		serverSocket.close();
	}

	public abstract void listenClients();
}
