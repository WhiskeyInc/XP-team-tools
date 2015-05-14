package tests;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import protocol.JsonMaker;
import protocol.JsonParser;
import client.model.ClientDetails;

public class JSONTest {

	private static final int TIMER = 2;

	@SuppressWarnings("unchecked")
	@Test
	public void JSONTest1() {

		JSONObject json = new JSONObject();
		json.put("richiesta", TIMER);
		json.put("Minuti", 26);
		json.put("Secondi", 30);

		JSONParser parser = new JSONParser();
		try {
			JSONObject json1 = (JSONObject) parser.parse(json.toString());
			assertEquals(json1.get("richiesta"), (long) 2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void chatRequestTest() throws Exception {

		String jsonString = JsonMaker.chatRequest("Ciao", "2");

		if (JsonParser.getRequest(jsonString) == Integer
				.parseInt(JsonMaker.CHAT)) {

			String[] vett = JsonParser.parseChatRequest(jsonString);

			assertEquals("Ciao", vett[1]);
			assertEquals(2, Integer.parseInt(vett[0]));
		} else {
			fail();
		}
	}

	@Test
	public void timerRequestTest() throws Exception {
		String jsonString = JsonMaker.timerRequest("LuBardo", 26, 30, null);

		if (JsonParser.getRequest(jsonString) == JsonParser.TIMER) {

			String[] vett = JsonParser.parseTimerRequest(jsonString);

			assertEquals("LuBardo", vett[0]);
			assertEquals(26, Integer.parseInt(vett[1]));
			assertEquals(30, Integer.parseInt(vett[2]));
		} else {
			fail();
		}
	}

	

	
	@Test
	public void connectToServerReqTest() throws Exception {
		ClientDetails det = new ClientDetails("Alb", "Test");
		ClientDetails det1 = JsonParser.parseConnectToServerRequest(JsonMaker.connectToServerRequest(det));
		assertEquals(det, det1);
	}
	
	@Test
	public void chatIndexRequestTest() throws Exception {
		assertEquals(2, JsonParser.parseChatIndexRequest(JsonMaker.chatIndexRequest(2)));
	}
	
	@Test
	public void disconnectTest() throws Exception {
		ClientDetails clientDetails = new ClientDetails("Alb", "Test");
		assertEquals(clientDetails, JsonParser.parseDisconnectRequest(JsonMaker.disconnectRequest(clientDetails)));
	}
	
	@Test
	public void addTeamMembReqTest() throws Exception {
		ClientDetails det =  new ClientDetails("Alb", "Test");
		
		assertEquals(det, JsonParser.parseAddTeamMembRequest(JsonMaker.addTeamMembRequest(det)));
	}
	

}
