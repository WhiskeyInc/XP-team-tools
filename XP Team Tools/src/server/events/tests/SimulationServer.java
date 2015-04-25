package server.events.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.model.AbstractServer;

/**
 * Class to simulate the second server to who we communicate the events
 * 
 * @author Nicola
 */
public class SimulationServer extends AbstractServer {

	private Socket clientSocket;

	public SimulationServer() {
		super();
	}

	/**
	 * It listen the arrival of clients and handle it, only printing the stream
	 */
	public void listenClients() {

		try {
			while (true) {
				clientSocket = serverSocket.accept();

				Runnable runnable = getRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Runnable getRunnable() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						String line = getLine();
						if (line != null) {
							System.out.println(line); // TODO
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		};
		return runnable;
	}

	private String getLine() throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		line = in.readLine();
		return line;
	}

}
