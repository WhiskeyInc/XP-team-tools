package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import timeline.Tool;

public class TimelineIntegrationTest {

	Tool tool = new Tool();

	@Test
	public void taskAdditionCreatesEvent() {
		tool.addTask("Nuovo task", "Integrare task in timeline");
		assertEquals(2, tool.getEventsNumber());
	}

	@Test
	public void taskDeletionCreatesEvent() {
		tool.addTask("Timer", "creare un timer che...");
		tool.addTask("Timeline", "creare una classe che...");
		tool.deleteTask("Timer");
		assertEquals(4, tool.getEventsNumber());
	}

	@Test
	public void automaticEventsTest() {
		String currentDate = getCurrentDate();
		tool.addTask("Timer", "");
		assertEquals("Created task: Timer",
				tool.getEvent("Created task: Timer").toString());
		assertEquals(currentDate,
				tool.getEvent("Created task: Timer").getDate());	
	}
	
	@Test
	public void taskModifyTest() {
		tool.addTask("Timer", "");
		tool.moveTaskToState("Timer", "DONE");
		assertEquals(3, tool.getEventsNumber());
		assertEquals("Changed state of task Timer: now it is DONE",
				tool.getEvent("Changed state of task Timer: now it is DONE").toString());
		assertEquals(getCurrentDate(),
				tool.getEvent("Changed state of task Timer: now it is DONE").getDate());
	}
	
	@Test
	public void participantAdditionCreatesEvent() throws Exception {
		assertEquals(1, tool.getEventsNumber());
		tool.addParticipantTo("Timeline", "usk");
		assertEquals("24 03 2015", tool.getEvent("Added usk to task: Timeline").getDate());
		assertEquals(2, tool.getEventsNumber());
	}

	private String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String currentDate = format.format(cal.getTime());
		return currentDate;
	}

}
