package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import server.model.JsonParser;
import client.model.JsonMaker;

public class JSONTest {

	private static final int TIMER = 2;

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
	public void JsonParserTest() throws Exception {
		
		
		String jsonString = JsonMaker.timerRequest("LuBardo", 26, 30);

//		JSONObject json = new JSONObject();
//		json.put(JsonParser.REQ, TIMER);
//		json.put(JsonParser.MINUTES, 26);
//		json.put(JsonParser.SECONDS, 30);

		if (JsonParser.getRequest(jsonString) == JsonParser.TIMER) {

			String[] vett = JsonParser.parseTimerRequest(jsonString);
			
			assertEquals("LuBardo", vett[0]);
			assertEquals(26, Integer.parseInt(vett[1]));
			assertEquals(30, Integer.parseInt(vett[2]));
		} else {
			fail();
		}
		jsonString = JsonMaker.chatRequest("LuBardo", "Ciao");
		
		
		if (JsonParser.getRequest(jsonString) == JsonParser.CHAT) {

			String[] vett = JsonParser.parseChatRequest(jsonString);
			
			assertEquals("LuBardo", vett[0]);
			assertEquals("Ciao", vett[1]);
		} else {
			fail();
		}
		

	}

}
