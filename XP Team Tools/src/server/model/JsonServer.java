package server.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import string.formatter.Formatter;

public class JsonServer extends AbstractServer {

	private HashMap<String, List<Socket>> clientMap = new HashMap<String, List<Socket>>();

	private IChatStorer chatStorer;
	private Socket clientSocket;
	private IMessageRecover recover;
	private BufferedReader in;
	public static final int NUM_OF_MESSAGES = 10;

	public JsonServer(IChatStorer chatStorer, IMessageRecover recover) {
		super();
		this.chatStorer = chatStorer;
		this.recover = recover;
	}

	public JsonServer(IChatStorer messageStorer) {
		super();
		this.chatStorer = messageStorer;
	}

	public JsonServer() {
		super();
	}

	@Override
	public void listenClients() {

		try {

			while (true) {
				clientSocket = serverSocket.accept();
				setInStream();
				String teamName = groupByTeam();

				try {
					alignClient(teamName);
				} catch (Exception e) {

				}

				Runnable runnable = getRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {

		}
	}

	private String groupByTeam() throws IOException {
		String teamName = getLine(in);
		System.out.println(teamName);
		if (clientMap.containsKey(teamName)) {
			clientMap.get(teamName).add(clientSocket);
		} else {
			List<Socket> socketList = new LinkedList<Socket>();
			socketList.add(clientSocket);
			clientMap.put(teamName, socketList);
		}
		return teamName;
	}

	private void setInStream() throws IOException {
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	// this is to get the messages sent to an offline client
	private void alignClient(String teamName) throws Exception {
		String[] messages = recoverMessages(teamName);
		for (int i = 0; i < messages.length; i++) {
			propagateMessage(messages[i], clientSocket);
		}
	}

	private Runnable getRunnable() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {

						String line = getLine(in);
						if (line != null) {
							int request = JsonParser.getRequest(line);
							switch (request) {
							case JsonParser.CHAT:
								String[] lines = JsonParser
										.parseChatRequest(line);
								String teamName = lines[0];
								String message = lines[1];
								System.out.println("Il team è: " + teamName);
								System.out.println(message); // TODO
								propagateMessageToTeamClients(line,
										clientMap.get(teamName));
								chatStorer.storeMessage(teamName, line);
								
								break;

							case JsonParser.TIMER: 
								//TODO
								break;
							default:
								break;
							}

						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

		};
		return runnable;
	}

	private String getLine(BufferedReader in) throws IOException {
		String line;
		line = in.readLine();
		return line;
	}

	private void propagateMessageToTeamClients(String message,
			List<Socket> clientSocketList) throws IOException {

		for (Socket socket : clientSocketList) {
			propagateMessage(message, socket);
		}
	}

	private void propagateMessage(String message, Socket socket)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		out.write(Formatter.appendNewLine(message));
		out.flush();
	}

	private String[] recoverMessages(String teamName) throws Exception {
		int numOfMessages = NUM_OF_MESSAGES;
		String[] sentMessages;

		if (recover.getNumOfMessages(teamName) < numOfMessages) {
			numOfMessages = recover.getNumOfMessages(teamName);
		}
		sentMessages = recover.recoverLastMessages(teamName, numOfMessages);

		return sentMessages;
	}

	// public String getLastMessage() {
	//
	// ArrayList<String> messages = chatStorer.getMessages();
	//
	// return messages.get(messages.size() - 1);
	// }

}
