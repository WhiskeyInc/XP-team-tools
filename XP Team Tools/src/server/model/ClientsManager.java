package server.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import string.formatter.Formatter;

/**
 * A Client Manager with the aim to have all the collection of clients connected
 * and with the purpose to work with them propagating messages and bringing
 * messages to align connected clients
 * 
 * @author nicola
 *
 */
public class ClientsManager {

	private HashMap<String, List<Socket>> clientMap = new HashMap<String, List<Socket>>();
	private IMessageRecover recover;

	public static final int NUM_OF_MESSAGES = 10;

	public ClientsManager(IMessageRecover recover) {
		super();
		this.recover = recover;
	}

	public void handleClient(Socket clientSocket, String teamName)
			throws IOException {

		updateClientsMap(clientSocket, teamName);

		try {
			alignClient(clientSocket, teamName);
		} catch (NoMessagesException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateClientsMap(Socket clientSocket, String teamName)
			throws IOException {
		System.out.println(teamName);

		if (clientMap.containsKey(teamName)) {
			clientMap.get(teamName).add(clientSocket);
		} else {
			List<Socket> socketList = new LinkedList<Socket>();
			socketList.add(clientSocket);
			clientMap.put(teamName, socketList);
		}
	}

	private void alignClient(Socket clientSocket, String teamName)
			throws NoMessagesException, IOException {
		String[] messages = recoverMessages(teamName);
		for (int i = 0; i < messages.length; i++) {
			propagateMessage(messages[i], clientSocket, teamName);
		}
	}

	private void propagateMessage(String message, Socket socket, String teamName)
			throws IOException {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			out.write(Formatter.appendNewLine(message));
			out.flush();
		} catch (SocketException e) {
			clientMap.remove(clientMap.get(teamName));
		}
	}

	private String[] recoverMessages(String teamName)
			throws NoMessagesException {
		int numOfMessages = NUM_OF_MESSAGES;
		String[] sentMessages;

		if (recover.getNumOfMessages(teamName) < numOfMessages) {
			numOfMessages = recover.getNumOfMessages(teamName);
		}
		sentMessages = recover.recoverLastMessages(teamName, numOfMessages);

		return sentMessages;
	}

	public HashMap<String, List<Socket>> getMap() {
		return clientMap;
	}

	public void propagateMessageToTeamClients(String message, String teamName)
			throws IOException {
		List<Socket> clientSocList = clientMap.get(teamName);
		for (Socket socket : clientSocList) {
			propagateMessage(message, socket, teamName);
		}
	}

}
