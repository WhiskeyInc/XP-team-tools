package client.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.model.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;

/**
 * An abstract client of the chat system.
 * The abstraction is to uncouple the model from the UI
 * 
 * @author alberto
 */
public abstract class AbstractClient {

	private Socket clientSocket;
	private String nickname;
	private String teamName;
	private DataOutputStream os;
	private DataInputStream is;
	
	
	public AbstractClient() {
		super();
		
	}

	public AbstractClient(String nickname) {
		super();
		this.nickname = nickname;
	}
	
	

	protected AbstractClient(String nickname, String teamName) {
		super();
		this.nickname = nickname;
		this.teamName = teamName;
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
			os.writeBytes(Formatter.appendNewLine((teamName)));
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	
	
	/**
	 * Sends a string message to the server to which it is connected 
	 * @param message Message to send
	 */
	public void sendMessageToServer(String message) {
		message = Formatter.appendNewLine(message);
		if (clientSocket != null && os != null && is != null) {
			try {
				//os.writeBytes(Formatter.markMessage(teamName));
				//os.writeBytes(Formatter.formatNickname(nickname));
				os.writeBytes(message);
				os.flush();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Reads from the socket messages
	 * @throws Exception TODO
	 */
	public void readFromSocket() throws Exception {

		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			while (true) {
				String read = input.readLine();
				if(read!= null) {
					//System.err.println(read);
					int request = JsonParser.getRequest(read);
					switch (request) {
					case JsonParser.CHAT:
						String[] lines = JsonParser
								.parseChatRequest(read);
						String message = lines[1];
						//System.out.println("Sono nel client " + message);
						handleMessage(message);
						break;

					case JsonParser.TIMER: 
						lines = JsonParser
						.parseTimerRequest(read);
						String timeStamp = 
								TimerFormatter.getDisplay(Integer.parseInt(lines[1]),
								Integer.parseInt(lines[2]));
						System.out.println(timeStamp);
						break;
					default:
						break;
					}
				}

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

	public String getNickname() {
		return nickname;
	}

	public String getTeamName() {
		return teamName;
	}
	/**
	 * It is the abstract method, used in @see readFromSocket method, that have to handle the
	 * message, for example printing it in UI
	 * @param message
	 */
	protected abstract void handleMessage(String message);
}
