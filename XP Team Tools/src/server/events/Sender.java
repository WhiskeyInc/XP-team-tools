package server.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import string.formatter.Formatter;
import client.model.JsonMaker;


public class Sender implements ISendEvent {

	private String host;
	private int port;
	
	private Socket clientSocket;
	private DataOutputStream os;
	private DataInputStream is;

	public Sender(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public void sendEventCreation(String eventName, ArrayList<String> participants) {
		openStreams();
		
		String message = JsonMaker.eventCommunication(eventName, participants);

		if (clientSocket != null && os != null && is != null) {
			try {
				os.writeBytes(message);
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		closeStreams();
	}
	
	private void openStreams() {
		try {
			clientSocket = new Socket(host, port);
			os = new DataOutputStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
			os.writeBytes(Formatter.appendNewLine(("I'm the ChatTimer application")));
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

//	public void readFromSocket() throws Exception {
//
//		BufferedReader input;
//		try {
//			input = new BufferedReader(new InputStreamReader(
//					clientSocket.getInputStream()));
//			while (true) {
//				String read = input.readLine();
//				if(read!= null) {
//					
//					System.out.println(read);
//
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	private void closeStreams() {
		try {

			os.close();
			is.close();
			clientSocket.close();
		} catch (IOException e) {

		}
	}

//	public static void post(String content) {
//
//
//		try {
//
//			// 1. URL
//			URL url = new URL("http://www.nicolalatella.it/");
//
//			// 2. Open connection
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//			// 3. Specify POST method
//			conn.setRequestMethod("POST");
//
//			// 4. Set the headers
//			conn.setRequestProperty("Content-Type", "application/json");
//			//conn.setRequestProperty("Authorization", "key=" + apiKey);
//
//			conn.setDoOutput(true);
//
//			// 5. Add JSON data into POST request body
//
//			// `5.1 Use Jackson object mapper to convert Contnet object into
//			// JSON
//			// ObjectMapper mapper = new ObjectMapper();
//
//			// 5.2 Get connection output stream
//			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//
//			// 5.3 Copy Content "JSON" into
//			// mapper.writeValue(wr, content);
//			
//			wr.writeBytes(JsonMaker.chatRequest("prov2", "bo"));
//			
//			// 5.4 Send the request
//			wr.flush();
//
//			// 5.5 close
//			wr.close();
//
//			// 6. Get the response
//			int responseCode = conn.getResponseCode();
//			System.out.println("\nSending 'POST' request to URL : " + url);
//			System.out.println("Response Code : " + responseCode);
//
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					conn.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// 7. Print result
//			System.out.println(response.toString());
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
