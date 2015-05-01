package client.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import protocol.JsonParser;
import string.formatter.Formatter;

/**
 * A client of the chat system, it sends and receives messages of one or more
 * conversations (chats) It is configurable adding implementation of
 * IClientService
 * 
 * @author Alberto
 */
public class StrategyClient {

	private Socket clientSocket;
	private String nickname;
	private String teamName;
	private DataOutputStream os;
	private DataInputStream is;
	private HashMap<Integer, IClientService> services = new HashMap<Integer, IClientService>();

	public StrategyClient() {
		super();

	}

	public StrategyClient(String nickname) {
		super();
		this.nickname = nickname;
	}

	public StrategyClient(String nickname, String teamName) {
		super();
		this.nickname = nickname;
		this.teamName = teamName;
	}

	/**
	 * Opens input and output stream to the given hostname at the given port
	 * 
	 * @param hostName
	 *            name of the host or ip address in the correct form (x.x.x.x)
	 * @param port
	 *            port of the server opened for the connection
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
	 * 
	 * @param message
	 *            Message to send
	 */
	public void sendMessageToServer(String message) {
		message = Formatter.appendNewLine(message);
		if (clientSocket != null && os != null && is != null) {
			try {
				// os.writeBytes(Formatter.markMessage(teamName));
				// os.writeBytes(Formatter.formatNickname(nickname));
				os.writeBytes(message);
				os.flush();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Reads from the socket messages
	 * 
	 * @throws Exception
	 *             TODO
	 */
	public void readFromSocket() throws Exception {

		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			while (true) {
				String read = input.readLine();
				if (read != null) {
					// System.out.println(read);
					IClientService service = services.get(JsonParser
							.getRequest(read));
					// TODO controllare se service non c'è... gestire
					if (service != null) {
						service.setAttribute(read);
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

	public void addService(int id, IClientService service) {
		services.put(id, service);
	}
}
