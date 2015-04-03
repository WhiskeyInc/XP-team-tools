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

import string.formatter.NewLineMaker;

/**
 * An implementation of the abstract class {@link AbstractServer} 
 * It carries many clients giving the duty of each one to a thread.
 * From each client it receives messages and at the same time it propagates them
 * to all the others
 * @author alberto
 */
public class TestableServer extends AbstractServer {

	private List<Socket> clientSocketList = new LinkedList<Socket>();
	private IChatStorer chatStorer;
	private Socket clientSocket;
	private IMessageRecover recover;
	public static final int NUM_OF_MESSAGES = 10;

	public TestableServer(IChatStorer chatStorer, IMessageRecover recover) {
		super();
		this.chatStorer = chatStorer;
		this.recover = recover;
	}

	public TestableServer(IChatStorer messageStorer) {
		super();
		this.chatStorer = messageStorer;
	}

	public TestableServer() {
		super();
	}

	@Override
	public void listenClients() {

		try {

			while (true) {
				clientSocket = serverSocket.accept();
				alignClient();
				clientSocketList.add(clientSocket);
				Runnable runnable = getRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {

		}
	}
	
	//function to propagate the messages sent to an offline client
	private void alignClient() throws IOException {
		String[] messages = recoverMessages();
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

						String line = getLine();

						if (line != null) {
							System.out.println(line); // TODO
							chatStorer.storeMessage(line);
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

	private String getLine() throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		line = in.readLine();
		return line;
	}

	private void propagateMessageToAllClients(String message) throws IOException {

		for (Socket socket : clientSocketList) {
			propagateMessage(message, socket);
		}
	}

	private void propagateMessage(String message, Socket socket)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		out.write(NewLineMaker.appendNewLine(message));
		out.flush();
	}

	private String[] recoverMessages() {
		int numOfMessages = NUM_OF_MESSAGES;
		String[] sentMessages;

		if (recover.getNumOfMessages() < numOfMessages) {
			numOfMessages = recover.getNumOfMessages();
		}
		sentMessages = recover.recoverLastMessages(numOfMessages);
		
		return sentMessages;
	}

	/**
	 * Function getting the last message
	 * @return the last message
	 */
	public String getLastMessage() {

		ArrayList<String> messages = chatStorer.getMessages();
		return messages.get(messages.size() - 1);
	}

}
