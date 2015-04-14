package tests;

import java.io.IOException;

import client.model.Client;

public class ClientMainConvManager {
	public static void main(String[] args) {
		final Client client = new Client("IncreMetal", "TeamFere");
		client.openStreams("localhost", 9999);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					client.readFromSocket();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		
		/*
		String message = "";
		message += Long.toString(System.currentTimeMillis()) + "%";
		message += client.getTeamName() + "%";
		message += client.getNickname() + "%";
		message += "Hi";
		*/
		client.sendMessageToServer("yeeee");


	}
}
