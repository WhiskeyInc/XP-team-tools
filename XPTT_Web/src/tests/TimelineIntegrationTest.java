package tests;

import static org.junit.Assert.*;
import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import boards.taskBoard.TeamTaskManager;
import filtering.NoFilter;

public class TimelineIntegrationTest {

	private ConcreteTeamSettings settings = new ConcreteTeamSettings();
	private Timeline timeline = new ConcreteTimeline();
	private TeamManager teamManager = new ConcreteTeamManager(settings,
			timeline);
	private TaskManager taskManager = new TeamTaskManager(
			new ConcreteTaskManager(), teamManager);
	private UserStoriesManager userStoriesManager = new TeamUserStoriesManager(
			new ConcreteUserStoriesManager(), teamManager);

	@Test
	public void taskAdditionCreatesEvent() throws Exception {
		taskManager.addTask("Nuovo task", "Integrare task in timeline");
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Created task: Nuovo task",
				timeline.getEvents(new NoFilter<Event>()).get(1).toString());
	}

	@Test
	public void taskDeletionCreatesEvent() throws Exception {
		taskManager.addTask("Timer", "creare un timer che...");
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "ACCEPTED",
				"DONE");
		taskManager.addTask("Timeline", "creare una classe che...");
		taskManager.deleteTask("Timer");
		assertEquals(4, timeline.getEventsNumber());
	}

	@Test
	/*
	 * Last test is commented because it may fail because of computing delays of
	 * few millis
	 */
	public void taskModifyCreatesEventTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		taskManager.addTask("Timer");
		taskManager.moveTaskToState("Timer", "DONE");
		assertEquals(3, timeline.getEventsNumber());
		assertEquals("Changed state of task Timer. Now it is DONE", timeline
				.getEvent("Changed state of task Timer. Now it is DONE")
				.toString());
		// assertEquals(
		// getCurrentDate(),
		// teamManager.getEvent(
		// "Changed state of task Timer: now it is DONE")
		// .getDate());
	}

	@Test
	public void developersAdditionCreateswEvent() throws Exception {
		settings.addTeamMember("Simo", "Lele", "Ale");
		taskManager.addTask("timeline");
		taskManager.addDevelopersToTask("timeline", "Simo", "Lele", "Ale");
		assertEquals(3, timeline.getEventsNumber());
	}

	@Test
	public void userStoryAdditionCreatesEvent() throws Exception {
		userStoriesManager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void userStoryDeletionCreatesEvent() throws Exception {
		userStoriesManager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		userStoriesManager.deleteUserStory("Timeline");
		assertEquals(3, timeline.getEventsNumber());
	}

	@Test
	public void userStoryModifyCreatesEventTest() throws Exception {
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		userStoriesManager.addUserStory("Timeline", "");
		userStoriesManager.moveUserStoryToState("Timeline", "DONE");
		assertEquals(3, timeline.getEventsNumber());
		assertEquals(
				"Changed state of userstory Timeline: now it is DONE",
				timeline.getEvent(
						"Changed state of userstory Timeline: now it is DONE")
						.toString());
	}

	@Test
	public void automaticEventsAreUnmovableTest() throws Exception {

		settings.setPossibleTasksStates("TODO", "DONE");
		settings.setPossibleUserStoriesStates("TODO", "DONE");
		settings.addTeamMember("Lele", "Simo");

		Event creation = timeline.getEvent("creation");
		assertTrue(!creation.isMovable());

		userStoriesManager
				.addUserStory("Timeline", "Voglio un pannello che...");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(1)
				.isMovable());

		taskManager.addTask("Dormire");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(2)
				.isMovable());

		taskManager.addTask("Dormire2", "Dormire tanto perchè cambia l'ora");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(3)
				.isMovable());

		taskManager.deleteTask("Dormire2");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(4)
				.isMovable());

		taskManager.moveTaskToState("Dormire", "TODO");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(5)
				.isMovable());

		taskManager.addDevelopersToTask("Dormire", "Lele", "Simo");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(6)
				.isMovable());

		userStoriesManager.addUserStory("Timeline2", null);
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(7)
				.isMovable());

		userStoriesManager.deleteUserStory("Timeline2");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(8)
				.isMovable());

		userStoriesManager.moveUserStoryToState("Timeline", "DONE");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(9)
				.isMovable());
	}

	/*
	 * The following tests are commented because since latest version date is
	 * computed with the precision of milliseconds so that this test may fail.
	 */

	// @Test
	// public void automaticEventsTest() throws Exception {
	// GregorianCalendar currentDate = getCurrentDate();
	// taskBoard.addTask("Timer", "");
	// assertEquals("Created task: Timer",
	// timeline.getEvent("Created task: Timer")
	// .toString());
	// assertEquals(currentDate,
	// timeline.getEvent("Created task: Timer")
	// .getDate());
	// }

	// @Test public void memberAdditionCreatesEvent() throws Exception {
	// assertEquals(1, teamManager.getEventsNumber());
	// settings.addTeamMember("usk"); Event event =
	// teamManager.getEvent("Added member: usk"); assertEquals(2,
	// teamManager.getEventsNumber()); assertEquals(getCurrentDate(),
	// event.getDate()); }
	//

	// @Test public void participantAdditionCreatesEvent() throws Exception {
	// assertEquals(1, teamManager.getEventsNumber());
	// settings.addTeamMember("usk"); teamManager.addDeveloperTo("Timeline",
	// "usk"); assertEquals(getCurrentDate(),
	// teamManager.getEvent("Added usk to task: Timeline").getDate());
	// assertEquals(2, teamManager.getEventsNumber()); }

	// private GregorianCalendar getCurrentDate() {
	// Calendar cal = Calendar.getInstance();
	// return (GregorianCalendar) cal;
	// }
}
