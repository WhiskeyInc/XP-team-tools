package client.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	private Socket clientSocket;
	private DataOutputStream os;
	private DataInputStream is;

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

	public void sendMessageToServer(String message) {
		if (clientSocket != null && os != null && is != null) {
			try {
				os.writeBytes(message);
				os.flush();
			} catch (Exception e) {

			}
		}
	}

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

	public void closeStream() {
		try {

			os.close();
			is.close();
			clientSocket.close();
		} catch (IOException e) {

		}
	}
}
