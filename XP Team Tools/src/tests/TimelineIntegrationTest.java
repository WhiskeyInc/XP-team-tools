package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.InvalidStateException;
import model.TeamManager;

import org.junit.Test;

import timeline.Event;

public class TimelineIntegrationTest {

	TeamManager teamManager = new TeamManager();

	@Test
	public void taskAdditionCreatesEvent() {
		teamManager.addTask("Nuovo task", "Integrare task in timeline");
		assertEquals(2, teamManager.getEventsNumber());
	}

	@Test
	public void taskDeletionCreatesEvent() {
		teamManager.addTask("Timer", "creare un timer che...");		teamManager.setPossibleStates("TODO", "IN PROGRESS", "ACCEPTED", "DONE");
		teamManager.addTask("Timeline", "creare una classe che...");
		teamManager.deleteTask("Timer");
		assertEquals(4, teamManager.getEventsNumber());
	}

	@Test
	public void automaticEventsTest() {
		String currentDate = getCurrentDate();
		teamManager.addTask("Timer", "");
		assertEquals("Created task: Timer",
				teamManager.getEvent("Created task: Timer").toString());
		assertEquals(currentDate,
				teamManager.getEvent("Created task: Timer").getDate());	
	}
	
	@Test
	public void taskModifyTest() throws InvalidStateException {
		teamManager.setPossibleStates("TODO", "IN PROGRESS", "DONE");
		teamManager.addTask("Timer", "");
		teamManager.moveTaskToState("Timer", "DONE");
		for (Event event : teamManager.getEvents()) {
			System.err.println(event.toString());;
		}
		assertEquals(3, teamManager.getEventsNumber());
		assertEquals("Changed state of task Timer: now it is DONE",
				teamManager.getEvent("Changed state of task Timer: now it is DONE").toString());
		assertEquals(getCurrentDate(),
				teamManager.getEvent("Changed state of task Timer: now it is DONE").getDate());
	}
	
	@Test
	public void participantAdditionCreatesEvent() throws Exception {
		assertEquals(1, teamManager.getEventsNumber());
		teamManager.addMember("usk");
		teamManager.addDeveloperTo("Timeline", "usk");
		assertEquals(getCurrentDate(), teamManager.getEvent("Added usk to task: Timeline").getDate());
		assertEquals(3, teamManager.getEventsNumber());
	}
	
	@Test
	public void participantAdditionFailsWhenInvalidParticipant() throws Exception {
		teamManager.addMember("sumo");
		try {
			teamManager.addDeveloperTo("Timeline", "ziobrando");
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void memberAdditionCreatesEvent() throws Exception {
		assertEquals(1, teamManager.getEventsNumber());
		teamManager.addMember("usk");
		Event event = teamManager.getEvent("Added member: usk");
		assertEquals(2, teamManager.getEventsNumber());
		assertEquals(getCurrentDate(), event.getDate());
	}
	
	private String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String currentDate = format.format(cal.getTime());
		return currentDate;
	}
	
	@Test
	public void eventsByTargetMember() throws Exception {
		teamManager.setPossibleStates("TODO", "IN PROGRESS", "DONE");
		teamManager.addMember("sumo");
		teamManager.addDeveloperTo("Timer", "sumo");
		teamManager.moveTaskToState("Timer", "DONE");
		teamManager.deleteTask("Timer");
		assertEquals(4, teamManager.getEvents("sumo").size());
	}

}
