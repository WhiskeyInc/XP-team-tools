package client.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

	private Socket clientSocket;
	private DataOutputStream os;
	private DataInputStream is;

	public void openStreams(String hostName, int port) {

		try {
			clientSocket = new Socket(hostName, port);
			 os = new DataOutputStream(
					clientSocket.getOutputStream());
			 is = new DataInputStream(
					clientSocket.getInputStream());
		} catch (Exception e) {

		}
	}

	public String sendMessage(String message) {

		return message;
	}

	public void sendMessageToServer(String message) {
		if (clientSocket != null && os != null && is != null) {
			try {
				os.writeBytes(message);
				os.close();
				is.close();
				clientSocket.close();
			} catch (Exception e) {

			}
		}
	}
}
