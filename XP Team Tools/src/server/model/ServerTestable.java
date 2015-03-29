package server.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTestable extends AbstractServer {

	private Socket clientSocket;
	private IChatStorer chatStorer;

	
	public ServerTestable(IChatStorer messageStorer) {
		super();
		this.chatStorer = messageStorer;
	}

	public ServerTestable() {
		super();
	}

	@Override
	public void listenClients() {

		try {
			
			String line;

			clientSocket = serverSocket.accept();

			while (true) {
				line = getLine();
				if (line != null) {
					chatStorer.storeMessage(line);
					propagateMessage(line);
				}
			}
			
			
		} catch (IOException e) {

		}
	}

	private String getLine() throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		line = in.readLine();
		return line;
	}

	private void propagateMessage(String message) throws IOException {

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(clientSocket.getOutputStream()));
		out.write(message);
		out.flush();
	}
	
	public String getLastMessage() {
		
		ArrayList<String> messages = chatStorer.getMessages();
		
		return messages.get(messages.size()-1);
	}

}
