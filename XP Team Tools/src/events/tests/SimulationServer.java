package events.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Class to simulate the second server to who we communicate the events
 * 
 * @author Nicola
 */
public class SimulationServer implements HttpHandler {

	@Override
	public void handle(HttpExchange ex) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				ex.getRequestBody()));

		System.out.println("Data received from "
				+ ex.getRemoteAddress().getHostName() + ":"
				+ ex.getRemoteAddress().getPort() + " ->");
		
		String data = "";	
		String temp = reader.readLine();
		while (temp != null) {
			data += temp;
			temp = reader.readLine();
		}
		System.out.println(data);

		String response = "Thank you for the data, beibi";
		ex.sendResponseHeaders(200, response.length());
		OutputStream os = ex.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
