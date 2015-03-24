package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerTest extends AbstractServer {

	private Socket clientSocket;
	private IMessageSaver messageSaver;

	@Override
	public void listenClients() {

		try {
			
			String line;
			while (true) {
				clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));
				line = in.readLine();
				if (line != null) {
					
					 messageSaver.saveMessage(line);
					// propagateMessage();
					System.out.println(line);
				}
			}
			
			
		} catch (IOException e) {

		}
	}

	public String getLastMessage() {
		return "Ciao";

	}

}
