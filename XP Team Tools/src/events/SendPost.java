package events;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import protocol.JsonMaker;

/**
 * Implementation of @IEventActionRequest
 * 
 * @author nicola
 *
 */
public class SendPost implements IEventActionRequest {

	private String url;

	public SendPost(String url) {
		super();
		this.url = url;
	}

	@Override
	public void sendAutomaticEventAction(String user, String eventName,
			ArrayList<String> participants, String id) {

		String json = JsonMaker.automaticEventRequest(user, eventName,
				participants, id);

		try {
			sendPost(url, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendManualEventAction(String user, String eventName,
			ArrayList<String> participants, Date date) {
		String jsonString = JsonMaker.manualEventRequest(user, eventName,
				participants, date);

		try {
			sendPost(url, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String sendJson(String json) {
		try {
			return sendPost(url, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}

	
	private String sendPost(String url, String data) throws Exception {

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
		
		String answer = "No Answer";
		if(responseCode != 500){
			System.out.println("Response Message : ");
	
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			answer = response.toString();
			// Print response message
			System.out.println(response.toString());
		}else{
			System.out.println("Error in the second server");
		}
		
		return answer;
	}
}
