package server.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import server.db.IDBConnection;
import server.utils.auth.Authenticate;
import string.formatter.Formatter;

/**
 * A Client Manager with the aim to have all the collection of clients connected
 * and with the purpose to work with them propagating messages and bringing
 * messages to align connected clients
 * 
 * @author Nicola, koelio
 *
 */
public class ClientsManagerAuth {

	private HashMap<String, List<Socket>> clientMap = new HashMap<String, List<Socket>>();
	private IMessageRecover recover;
	private IDBConnection connection;
	private Authenticate auth = new Authenticate();
	private String nickname;
	private String pwd;

	public static final int NUM_OF_MESSAGES = 10;

	public ClientsManagerAuth(IMessageRecover recover, IDBConnection connection) {
		super();
		this.recover = recover;
		this.connection = connection;
	}

	/**
	 * Handles all the operations to do when a new client connects to the
	 * Server, including authentication of the client;
	 * 
	 * @param clientSocket
	 *            Socket of the client connecting
	 * @param teamName
	 *            Name of his team
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 *             relates to authentication methods in {@link Authenticate}
	 *             TODO to be handled
	 * @throws SQLException
	 *             relates to {@link IDBConnection} when a connection through
	 *             JDBC is established TODO to be handled
	 */
	public void handleClient(Socket clientSocket, String teamName)
			throws IOException, NoSuchAlgorithmException, SQLException {

		if (authenticate(nickname, pwd)) {
			updateClientsMap(clientSocket, teamName);
		} else {
			// throw new IOException("The user " + nickname
			// + "does not exist or invalid password");
			System.out.println("Utente non autenticato!");
		}

		try {
			alignClient(clientSocket, teamName);
		} catch (NoMessagesException e) {
			// e.printStackTrace();
			// TODO
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
			// TODO
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

	/**
	 * It propagates a message to all the clients of a team
	 * 
	 * @param message
	 *            Message to propagate
	 * @param teamName
	 *            Team
	 * @throws IOException
	 */
	public void propagateMessageToTeamClients(String message, String teamName)
			throws IOException {
		List<Socket> clientSocList = clientMap.get(teamName);
		for (Socket socket : clientSocList) {
			propagateMessage(message, socket, teamName);
		}
	}

	/**
	 * It removes a specified client from every team conversation he's inside
	 * 
	 * @param clientToRemove
	 */
	public void removeClient(Socket clientToRemove) {
		for (HashMap.Entry<String, List<Socket>> entry : clientMap.entrySet()) {
			List<Socket> clients = entry.getValue();
			clients.remove(clientToRemove);
		}
	}

	private boolean authenticate(String nickname, String pwd)
			throws IOException, NoSuchAlgorithmException, SQLException { // TODO

		boolean autheniticated = auth.authenticate(connection.getConnection(),
				nickname, pwd);

		return autheniticated;
	}

	public void setAuth(String authData) {
		StringTokenizer tok = new StringTokenizer(authData, "\t");
		// System.out.println(tok.nextToken());
		// System.out.println(tok.nextToken());
		// authData[0]= " ";
		// authData[1]= " ";
		nickname = tok.nextToken();
		pwd = tok.nextToken();

	}

}
