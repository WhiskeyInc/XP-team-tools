package tests.JUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import protocol.JsonMaker;
import protocol.JsonParser;
import client.model.ClientDetails;
import client.model.MacroEvents;

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
		String jsonString = JsonMaker.timerRequest("LuBardo", 26, 30, null,
				"id");

		if (JsonParser.getRequest(jsonString) == JsonParser.TIMER) {

			String[] vett = JsonParser.parseTimerRequest(jsonString);

			assertEquals("LuBardo", vett[0]);
			assertEquals(26, Integer.parseInt(vett[1]));
			assertEquals(30, Integer.parseInt(vett[2]));
			assertEquals("id", vett[3]);
		} else {
			fail();
		}
	}

	@Test
	public void connectToServerReqTest() throws Exception {
		ClientDetails det = new ClientDetails("Alb", "Test");
		ClientDetails det1 = JsonParser.parseConnectToServerRequest(JsonMaker
				.connectToServerRequest(det));
		assertEquals(det, det1);
	}

	@Test
	public void chatIndexRequestTest() throws Exception {
		assertEquals(2,
				JsonParser.parseChatIndexRequest(JsonMaker.chatIndexRequest(2)));
	}

	@Test
	public void disconnectTest() throws Exception {
		ClientDetails clientDetails = new ClientDetails("Alb", "Test");
		assertEquals(clientDetails, JsonParser.parseDisconnectRequest(JsonMaker
				.disconnectRequest(clientDetails)));
	}

	@Test
	public void addTeamMembReqTest() throws Exception {
		ClientDetails det = new ClientDetails("Alb", "Test");

		assertEquals(det, JsonParser.parseAddTeamMembRequest(JsonMaker
				.addTeamMembRequest(det)));
	}

	@Test
	public void makeListOfTeamReqTest() throws Exception {
		String[] teams = new String[3];
		teams[0] = "Test1";
		teams[1] = "Test2";
		teams[2] = "Test3";
		assertArrayEquals(teams,
				JsonParser.parseMakeTeamMembs(JsonMaker.makeListOfTeams(teams)));
	}

	@Test
	public void newChatReqTest() throws Exception {
		ClientDetails[] det = new ClientDetails[3];
		det[0] = new ClientDetails("A", "tA");
		det[1] = new ClientDetails("B", "tB");
		det[2] = new ClientDetails("C", "tC");
		assertArrayEquals(det,
				JsonParser.parseNewChatRequest(JsonMaker.newChatRequest(det)));
	}

	@Test
	public void newTeamReqTest() throws Exception {
		String[] s = new String[2];
		s[0] = "Test";
		s[1] = "Tester";
		assertArrayEquals(s, JsonParser.parseNewTeamRequest(JsonMaker
				.newTeamRequest(s[0], s[1])));
	}

	@Test
	public void teamMembsReqTest() throws Exception {
		ClientDetails det = new ClientDetails("Me", "Test");
		assertEquals(
				det,
				JsonParser.parseTeamMembsRequest(JsonMaker.teamMembsRequest(
						det.getNickname(), det.getTeamName())));
	}

	@Test
	public void teamListReqTest() throws Exception {
		String name = "Me";
		assertEquals(name, JsonParser.parseTeamsListRequest(JsonMaker
				.teamsListRequest(name)));
	}

	@Test
	public void manualEventReqTest() throws Exception {
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Don Lele");
		participants.add("Nick the German");
		participants.add("Simo");
		participants.add("Digio");

		// sender.sendAutomaticEventAction("admin", "communicationTest",
		// participants, "id");
	
		@SuppressWarnings("deprecation")
		String json = JsonMaker.manualEventRequest("admin",
				"communicationTestManual", participants, new Date(2050, 2, 12, 12, 12));
		assertEquals(
				"{\"request\":\"11\",\"min\":\"12\",\"month\":\"2\",\"hour\":\"12\",\"year\":\"2050\",\"action\":\"addEvent\",\"event_name\":\"communicationTestManual\",\"user\":\"admin\",\"day\":\"12\",\"participants\":[\"Don Lele\",\"Nick the German\",\"Simo\",\"Digio\"]}",
				json);
	}

	@Test
	public void automaticEventReqTest() throws Exception {
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Don Lele");
		participants.add("Nick the German");
		participants.add("Simo");
		participants.add("Digio");
		String json = JsonMaker.automaticEventRequest("admin",
				"communicationTest", participants, "id");

		assertEquals(
				"{\"request\":\"11\",\"action\":\"addAutomaticEvent\",\"event_name\":\"communicationTest\",\"id\":\"id\",\"user\":\"admin\",\"participants\":[\"Don Lele\",\"Nick the German\",\"Simo\",\"Digio\"]}",
				json);
	}

	@Test
	public void requestMacroEventsListTest() throws Exception {

		String json = JsonMaker.requestMacroEventsList("admin");
		assertEquals(
				"{\"request\":\"14\",\"action\":\"macroEventsRequest\",\"user\":\"admin\"}",
				json);
	}

	@Test
	public void replyMacroEventsListTest() throws Exception {

		MacroEvents ev = JsonParser
				.parseMacroEventsResponse("{\"ids\":[8],\"names\":[\"Third Release\"],\"action\":\"macro_event_response\",\"user\":\"admin\"}");
		
		assertEquals(ev.getNames().get(0), "Third Release");
	}
}
