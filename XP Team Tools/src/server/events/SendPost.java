package server.events;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import client.model.JsonMaker;

public class SendPost implements IEventActionRequest {

	private String url;

	public SendPost(String url) {
		super();
		this.url = url;
	}

	@Override
	public void sendEventAction(String eventAction, String eventName,
			ArrayList<String> participants) {

		String json = JsonMaker.eventCommunication(eventAction, eventName,
				participants);

		try {
			sendPost(url, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendPost(String url, String data) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// Add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Java XP-Team-Tools Application");
		con.setRequestProperty("Content-Type", "application/json");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();

		// Get response
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + data);
		System.out.println("Response Code : " + responseCode);
		System.out.println("Response Message : ");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Print response message
		System.out.println(response.toString());
	}
}
