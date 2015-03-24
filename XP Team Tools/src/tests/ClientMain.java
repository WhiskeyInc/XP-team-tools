package tests;

import client.model.Client;

public class ClientMain {
	public static void main(String[] args) {
		Client client = new Client();
		client.openStreams("localhost", 9999);
		client.sendMessageToServer("taooooooooooooooooooooooooooooooooooooooooo");

	}
}
