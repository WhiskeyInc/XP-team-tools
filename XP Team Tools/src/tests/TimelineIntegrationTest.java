package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.InvalidStateException;
import model.TeamManager;
import model.TeamSettings;

import org.junit.Test;

import filtering.MemberFilter;
import filtering.NoFilter;
import filtering.TargetFilter;
import timeline.Event;

public class TimelineIntegrationTest {

	TeamSettings settings = new TeamSettings();
	TeamManager teamManager = new TeamManager(settings);

	@Test
	public void taskAdditionCreatesEvent() throws Exception {
		teamManager.addUserStory("Notifiche temporali", "voglio che...");
		teamManager.addTaskToUserStory("Nuovo task",
				"Integrare task in timeline", "Notifiche temporali");
		assertEquals(3, teamManager.getEventsNumber());
	}

	@Test
	public void taskDeletionCreatesEvent() throws Exception {
		teamManager.addGeneralTask("Timer", "creare un timer che...");
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "ACCEPTED",
				"DONE");
		teamManager.moveTaskToState("Timer", "DONE");
		teamManager.deleteTask("Timer");
		assertEquals(4, teamManager.getEventsNumber());
	}

	@Test
	public void automaticEventsTest() throws Exception {
		GregorianCalendar currentDate = getCurrentDate();
		teamManager.addGeneralTask("Timer");
		assertEquals("Created task: Timer",
				teamManager.getEvent("Created task: Timer").toString());
		assertEquals(currentDate, teamManager.getEvent("Created task: Timer")
				.getDate());
	}

	@Test
	/*
	 * Last test is commented because it may fail because of computing delays of
	 * few millis
	 */
	public void taskModifyTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		teamManager.addUserStory("Notifiche temporali");
		teamManager.addTaskToUserStory("Timer", "", "Notifiche temporali");
		teamManager.moveTaskToState("Timer", "DONE");
		for (Event event : teamManager.getEvents(new NoFilter<Event>())) {
			System.err.println(event.toString());
			;
		}
		assertEquals(4, teamManager.getEventsNumber());
		assertEquals(
				"Changed state of task Timer of: Notifiche temporali. Now it is DONE",
				teamManager
						.getEvent(
								"Changed state of task Timer of: Notifiche temporali. Now it is DONE")
						.toString());
		// assertEquals(
		// getCurrentDate(),
		// teamManager.getEvent(
		// "Changed state of task Timer: now it is DONE")
		// .getDate());
	}

	// this test is commented because since latest version date is computed with
	// the precision of milliseconds so that this test may fail
	/*
	 * @Test public void participantAdditionCreatesEvent() throws Exception {
	 * assertEquals(1, teamManager.getEventsNumber());
	 * settings.addTeamMember("usk"); teamManager.addDeveloperTo("Timeline",
	 * "usk"); assertEquals(getCurrentDate(),
	 * teamManager.getEvent("Added usk to task: Timeline").getDate());
	 * assertEquals(2, teamManager.getEventsNumber()); }
	 */

	@Test
	public void participantAdditionFailsWhenInvalidParticipant()
			throws Exception {
		settings.addTeamMember("sumo");
		teamManager.addGeneralTask("Timeline");
		try {
			teamManager.addDeveloperTo("Timeline", "ziobrando");
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/*
	 * @Test public void memberAdditionCreatesEvent() throws Exception {
	 * assertEquals(1, teamManager.getEventsNumber());
	 * settings.addTeamMember("usk"); Event event =
	 * teamManager.getEvent("Added member: usk"); assertEquals(2,
	 * teamManager.getEventsNumber()); assertEquals(getCurrentDate(),
	 * event.getDate()); }
	 */

	private GregorianCalendar getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return (GregorianCalendar) cal;
	}

	@Test
	public void eventsByTargetMember() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		settings.addTeamMember("sumo");
		teamManager.addUserStory("Client WEB");
		teamManager.addTaskToUserStory("Timer", "Client WEB");
		teamManager.addDeveloperTo("Timer", "sumo");
		teamManager.moveTaskToState("Timer", "DONE");
		teamManager.deleteTask("Timer");
		assertEquals(
				3,
				teamManager.getEvents(
						new TargetFilter<Event>(new MemberFilter("sumo")))
						.size());
	}

	@Test
	public void userStoryAdditionCreatesEvent() throws Exception {
		teamManager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(2, teamManager.getEventsNumber());
	}

	@Test
	public void userStoryDeletionCreatesEvent() throws Exception {
		teamManager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "ACCEPTED",
				"DONE");
		teamManager.deleteUserStory("Timeline");
		assertEquals(3, teamManager.getEventsNumber());
	}

	@Test
	public void userStoryModifyTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		settings.setPossibleUserStoriesStates("TODO", "IN PRORESS",
				"ACCOMPLISHED");
		teamManager.addUserStory("Timeline", "");
		teamManager.moveStoryToState("Timeline", "ACCOMPLISHED");
		for (Event event : teamManager.getEvents(new NoFilter<Event>())) {
			System.err.println(event.toString());
			;
		}
		assertEquals(3, teamManager.getEventsNumber());
		assertEquals(
				"Changed state of userstory Timeline: now it is ACCOMPLISHED",
				teamManager
						.getEvent(
								"Changed state of userstory Timeline: now it is ACCOMPLISHED")
						.toString());
	}
}
