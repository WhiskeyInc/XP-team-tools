package client.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import string.formatter.Formatter;
import string.formatter.IStringFormatter;
import string.formatter.NickNameFormatter;

/**
 * A client of the chat system, it sends and receives messages of one or more 
 * conversations (chats)
 * 
 * @author alberto
 */
public class Client {

	private Socket clientSocket;
	private String nickname;
	private DataOutputStream os;
	private DataInputStream is;
	
	private IStringFormatter formatter;
	
	public Client() {
		super();
		
		this.formatter = new Formatter();
	}

	public Client(String nickname) {
		super();
		this.nickname = nickname;
		this.formatter = new Formatter();
	}

	/**
	 * Opens input and output stream to the given hostname at the given port
	 * @param hostName name of the host or ip address in the correct form (x.x.x.x)
	 * @param port port of the server opened for the connection
	 */
	public void openStreams(String hostName, int port) {
		try {
			clientSocket = new Socket(hostName, port);
			os = new DataOutputStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	

	public String sendMessage(String message) {

		return message;
	}

	/**
	 * Sends a string message to the server to which it is connected 
	 * @param message Message to send
	 */
	public void sendMessageToServer(String message) {
		if (clientSocket != null && os != null && is != null) {
			try {
				os.writeBytes(formatter.formatMessage(nickname));
				os.writeBytes(message);
				os.flush();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Reads from the socket messages
	 * @throws IOException when doens't find the input stream
	 */
	public void readFromSocket() throws IOException {

		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			while (true) {
				String read = input.readLine();
				if(read!= null)
					System.out.println("Sono nel client " + read );

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Closes the input/output stream and the socket
	 */
	public void closeStream() {
		try {

			os.close();
			is.close();
			clientSocket.close();
		} catch (IOException e) {

		}
	}
}
