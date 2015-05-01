package tests;

import protocol.JsonMaker;
import client.model.Client2;

public class TimerJsonClient1 {
	public static void main(String[] args) {
		final Client2 client = new Client2("IncreMetal", "TeamFere");
		client.openStreams("localhost", 9999);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					client.readFromSocket();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		final String teamName = client.getTeamName();
		client.sendMessageToServer(JsonMaker.timerRequest(teamName, 0, 7));


	}
}
