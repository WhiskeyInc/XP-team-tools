package client.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

import server.model.JsonParser;
import string.formatter.Formatter;
import timer.TimerFormatter;

/**
 * A client of the chat system, it sends and receives messages of one or more 
 * conversations (chats)
 * The connection between model and ui is handled with an observer pattern
 * There are two inner classes describing current timestamp and current message which are observable
 * @author nicola
 */
public class ObservableClient{

	private Socket clientSocket;
	private String nickname;
	private String teamName;
	private DataOutputStream os;
	private DataInputStream is;
	
	public class CurrentTimestamp extends Observable{
		private String currentTimestamp = "00:10";

		public String getCurrentTimestampString() {
			return currentTimestamp;
		}
		public void setCurrentTimestamp(String currentTimestamp) {
			this.currentTimestamp = currentTimestamp;
			update();
		}
		private void update() {
			setChanged();
			notifyObservers();
		}
	}
	
	public class CurrentMessage extends Observable{
		private String currentMessage="";

		public String getCurrentMessageString() {
			return currentMessage;
		}
		public void setCurrentMessage(String currentMessage) {
			this.currentMessage = currentMessage;
			update();
		}
		private void update() {
			setChanged();
			notifyObservers();
		}
		
	}
	
	private CurrentTimestamp currentTimestamp = new CurrentTimestamp();
	private CurrentMessage currentMessage = new CurrentMessage();
	
	public ObservableClient() {
		super();
		
	}

	public ObservableClient(String nickname) {
		super();
		this.nickname = nickname;
	}
	

	public ObservableClient(String nickname, String teamName) {
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
					//System.out.println(read);
					int request = JsonParser.getRequest(read);
					switch (request) {
					case JsonParser.CHAT:
						String[] lines = JsonParser
								.parseChatRequest(read);
						String message = lines[1];
						//System.out.println("Sono nel client " + message);
						setCurrentMessage(message);
						break;

					case JsonParser.TIMER: 
						lines = JsonParser
						.parseTimerRequest(read);
						String timeStamp = 
								TimerFormatter.getDisplay(Integer.parseInt(lines[1]),
								Integer.parseInt(lines[2]));
						setCurrentTimestamp(timeStamp);
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

	public void setCurrentTimestamp(String currentTimestamp) {
		this.currentTimestamp.setCurrentTimestamp(currentTimestamp);
	}

	public void setCurrentMessage(String currentMessage) {
		this.currentMessage.setCurrentMessage(currentMessage);
	}

	public CurrentTimestamp getCurrentTimestamp() {
		return currentTimestamp;
	}

	public CurrentMessage getCurrentMessage() {
		return currentMessage;
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
}
