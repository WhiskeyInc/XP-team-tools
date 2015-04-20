package server.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import string.formatter.Formatter;

/**
 * A testable server with message recovery functions
 * 
 * @author alberto
 *
 */
public class TestableServerRecover extends AbstractServer {

	private List<Socket> clientSocketList = new LinkedList<Socket>();
	private IChatStorer chatStorer;
	private Socket clientSocket;
	private IMessageRecover recover;
	private BufferedReader in;
	public static final int NUM_OF_MESSAGES = 10;

	public TestableServerRecover(IChatStorer chatStorer, IMessageRecover recover) {
		super();
		this.chatStorer = chatStorer;
		this.recover = recover;
	}

	public TestableServerRecover(IChatStorer messageStorer) {
		super();
		this.chatStorer = messageStorer;
	}

	public TestableServerRecover() {
		super();
	}

	@Override
	public void listenClients() {

		try {

			while (true) {
				clientSocket = serverSocket.accept();
				setInStream();
				alignClient();
				clientSocketList.add(clientSocket);
				Runnable runnable = getRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {

		}
	}

	/**
	 * Sets input stream
	 * @throws IOException
	 */
	private void setInStream() throws IOException {
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	/**
	 * Handles an alignement function: message sent when a user was offline
	 * are recovered and propagated
	 * @throws IOException
	 */
	private void alignClient() throws IOException {
		String[] messages = recoverMessages();
		for (int i = 0; i < messages.length; i++) {
			propagateMessage(messages[i], clientSocket);
		}
	}

	private Runnable getRunnable() {
		Runnable runnable = new Runnable() {
			final BufferedReader input = in;
			@Override
			public void run() {
				while (true) {
					try {

						String line = input.readLine();	
						
						if (line != null) {
							System.out.println(line); // TODO
							chatStorer.storeMessage(null, line);
							propagateMessageToAllClients(line);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

		};
		return runnable;
	}

	private void propagateMessageToAllClients(String message)
			throws IOException {

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

	/**
	 * Recovers messages sent when a user is offline
	 * @return
	 */
	private String[] recoverMessages() {
		int numOfMessages = NUM_OF_MESSAGES;
		String[] sentMessages = null;
		try {

			if (recover.getNumOfMessages(null) < numOfMessages) {
				numOfMessages = recover.getNumOfMessages(null);
			}
			sentMessages = recover.recoverLastMessages(null, numOfMessages);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sentMessages;
	}

	public String getLastMessage() {

		Map<String, ArrayList<String>> mapMessages = chatStorer.getMessages();
		Set<String> keys = mapMessages.keySet();
		ArrayList<String> messages = new ArrayList<String>();
		for (String key : keys) {
			messages.addAll(mapMessages.get(key));
		}

		return messages.get(messages.size() - 1);
	}

}
