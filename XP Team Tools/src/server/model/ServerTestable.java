package server.model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ServerTestable extends AbstractServer {

	private Socket clientSocket;
	private IMessageSaver messageSaver;
	private ArrayList<String> messageList = new ArrayList<String>();

	@Override
	public void listenClients() {

		try {
			
			String line;

			clientSocket = serverSocket.accept();

			while (true) {

				BufferedReader in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));
				line = in.readLine();
				if (line != null) {
					messageList.add(line);
//					PrintWriter out = 
//							new PrintWriter(clientSocket.getOutputStream());
//					out.print(line);
//					out.flush();

					BufferedWriter out = new BufferedWriter(
							new OutputStreamWriter(clientSocket.getOutputStream()));
					out.write(line);
					out.flush();
					// messageSaver.saveMessage(line);
					// propagateMessage();
					System.out.println(line);
				}
			}
			
			
		} catch (IOException e) {

		}
	}

	public String getLastMessage() {
		
		int messages = messageList.size();
		
		return messageList.get(messages-1);

	}

}
