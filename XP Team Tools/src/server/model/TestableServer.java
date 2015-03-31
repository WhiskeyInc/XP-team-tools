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

public class TestableServer extends AbstractServer {

	private List<Socket> clientSocketList = new LinkedList<Socket>();
	private IChatStorer chatStorer;
	private Socket clientSocket;

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
				clientSocketList.add(clientSocket);
				Runnable runnable = getRunnable();
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {

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
							System.out.println(line); //TODO
							chatStorer.storeMessage(line);
							propagateMessage(line);
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

	private void propagateMessage(String message) throws IOException {

		for (Socket socket : clientSocketList) {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			out.write(NewLineMaker.appendNewLine(message));
			out.flush();
		}
	}

	public String getLastMessage() {

		ArrayList<String> messages = chatStorer.getMessages();

		return messages.get(messages.size() - 1);
	}

}
