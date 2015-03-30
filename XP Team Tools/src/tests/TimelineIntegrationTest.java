package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.GregorianCalendar;

import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;
import model.exceptions.InvalidMemberException;
import model.exceptions.UnmovableEventException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import boards.ConcreteTaskManager;
import boards.ConcreteUserStoriesManager;
import boards.TaskManager;
import boards.TeamTaskManager;
import boards.TeamUserStoriesManager;
import boards.UserStoriesManager;
import filtering.TargetFilter;
import filtering.chechers.ParticipantsEventChecker;

public class TimelineIntegrationTest {

	private ConcreteTeamSettings settings = new ConcreteTeamSettings();
	private Timeline timeline = new ConcreteTimeline();
	private TeamManager teamManager = new ConcreteTeamManager(settings, timeline);
	private TaskManager taskBoard = new TeamTaskManager(new ConcreteTaskManager(),
			teamManager);
	private UserStoriesManager userStoriesBoard = new TeamUserStoriesManager(
			new ConcreteUserStoriesManager(), teamManager);

	@Test
	public void taskAdditionCreatesEvent() throws Exception {
		taskBoard.addTask("Nuovo task", "Integrare task in timeline");
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void taskDeletionCreatesEvent() throws Exception {
		taskBoard.addTask("Timer", "creare un timer che...");
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "ACCEPTED",
				"DONE");
		taskBoard.addTask("Timeline", "creare una classe che...");
		taskBoard.deleteTask("Timer");
		assertEquals(4, timeline.getEventsNumber());
	}

	@Test
	public void automaticEventsTest() throws Exception {
//		GregorianCalendar currentDate = getCurrentDate();
		taskBoard.addTask("Timer", "");
		assertEquals("Created task: Timer",
				timeline.getEvent("Created task: Timer")
						.toString());
//		assertEquals(currentDate,
//				timeline.getEvent("Created task: Timer")
//						.getDate());
	}

	@Test
	/*
	 * Last test is commented because it may fail because of computing delays of
	 * few millis
	 */
	public void taskModifyTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		taskBoard.addTask("Timer");
		taskBoard.moveTaskToState("Timer", "DONE");
		assertEquals(3, timeline.getEventsNumber());
		assertEquals(
				"Changed state of task Timer. Now it is DONE",
				timeline
						.getEvent(
								"Changed state of task Timer. Now it is DONE")
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
		try {
			taskBoard.addDevelopersToTask("Timeline", "ziobrando");
			fail();
		} catch (InvalidMemberException e) {
			assertTrue(true);
		}
	}

	// this test is commented because since latest version date is computed with
	// the precision of milliseconds so that this test may fail
	/*
	 * @Test public void memberAdditionCreatesEvent() throws Exception {
	 * assertEquals(1, teamManager.getEventsNumber());
	 * settings.addTeamMember("usk"); Event event =
	 * teamManager.getEvent("Added member: usk"); assertEquals(2,
	 * teamManager.getEventsNumber()); assertEquals(getCurrentDate(),
	 * event.getDate()); }
	 */

	@Test
	public void eventsByTargetMember() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		settings.addTeamMember("sumo");
		taskBoard.addTask("Timer");
		taskBoard.addDevelopersToTask("Timer", "sumo");
		taskBoard.moveTaskToState("Timer", "DONE");
		taskBoard.deleteTask("Timer");
		for (Event event : timeline.getEvents(new TargetFilter<Event>(
				new ParticipantsEventChecker("sumo")))) {
			System.err.println(event.toString());
		}
		assertEquals(
				3,
				timeline.getEvents(
						new TargetFilter<Event>(new ParticipantsEventChecker(
								"sumo"))).size());
	}

	@Test
	public void userStoryAdditionCreatesEvent() throws Exception {
		userStoriesBoard.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void userStoryDeletionCreatesEvent() throws Exception {
		userStoriesBoard.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		userStoriesBoard.deleteUserStory("Timeline");
		assertEquals(3, timeline.getEventsNumber());
	}

	@Test
	public void userStoryModifyTest() throws Exception {
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		userStoriesBoard.addUserStory("Timeline", "");
		userStoriesBoard.moveUserStoryToState("Timeline", "DONE");
		assertEquals(3, timeline.getEventsNumber());
		assertEquals(
				"Changed state of userstory Timeline: now it is DONE",
				timeline.getEvent(
						"Changed state of userstory Timeline: now it is DONE")
						.toString());
	}

	@Test
	public void unMovableEventsAreUnMovable() throws Exception {
		try {
			timeline.moveEvent("creation", new GregorianCalendar(2015, 1,
					12, 12, 45, 34));
			fail();
		} catch (UnmovableEventException e) {
			assertTrue(true);
		}
	}

//	private GregorianCalendar getCurrentDate() {
//		Calendar cal = Calendar.getInstance();
//		return (GregorianCalendar) cal;
//	}
}
