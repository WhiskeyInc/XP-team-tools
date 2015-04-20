package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.Member;
import model.TeamManager;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
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
				.getEvent(2).toString());
		// assertEquals(
		// getCurrentDate(),
		// teamManager.getEvent(
		// "Changed state of task Timer: now it is DONE")
		// .getDate());
	}

	@Test
	public void developersAdditionCreateswEvent() throws Exception {
		settings.setManager(teamManager);
		settings.addTeamMember(new Member("Simo", "Colucci", "DP"), new Member(
				"Lele", "Fabbiani", "Test"), new Member("Ale", "Incre",
				"OpenSource"));
		taskManager.addTask("timeline");
		taskManager.addDevelopersToTask("timeline", "Simo Colucci", "Lele Fabbiani", "Ale Incre");
		assertEquals(4, timeline.getEventsNumber());
	}

	@Test
	public void userStoryAdditionCreatesEvent() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		userStoriesManager.addUserStory(new UserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void userStoryDeletionCreatesEvent() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		userStoriesManager.addUserStory(new UserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		userStoriesManager.deleteUserStory("Timeline");
		assertEquals(3, timeline.getEventsNumber());
	}

	@Test
	public void userStoryModifyCreatesEventTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		userStoriesManager.addUserStory(new UserStory("Timeline", "",
				taskmanager));
		userStoriesManager.moveUserStoryToState("Timeline", "DONE");
		assertEquals(3, timeline.getEventsNumber());
		assertEquals("Changed state of userstory Timeline: now it is DONE",
				timeline.getEvent(2).toString());
	}

	@Test
	public void automaticEventsAreUnmovableTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		TaskManager taskmanager2 = new ConcreteTaskManager();
		settings.setManager(teamManager);
		settings.setPossibleTasksStates("TODO", "DONE");
		settings.setPossibleUserStoriesStates("TODO", "DONE");
		settings.addTeamMember(new Member("Simo", "Colucci", "DP"), new Member(
				"Lele", "Fabbiani", "Test"));

		Event creation = timeline.getEvent(0);
		assertTrue(!creation.isEditable());

		userStoriesManager.addUserStory(new UserStory("Timeline",
				"Voglio un pannello che...", taskmanager));
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(1)
				.isEditable());

		taskManager.addTask("Dormire");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(2)
				.isEditable());

		taskManager.addTask("Dormire2", "Dormire tanto perchè cambia l'ora");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(3)
				.isEditable());

		taskManager.deleteTask("Dormire2");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(4)
				.isEditable());

		taskManager.moveTaskToState("Dormire", "TODO");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(5)
				.isEditable());

		taskManager.addDevelopersToTask("Dormire", "Lele Fabbiani",
				"Simo Colucci");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(6)
				.isEditable());

		userStoriesManager.addUserStory(new UserStory("Timeline2", null,
				taskmanager2));
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(7)
				.isEditable());

		userStoriesManager.deleteUserStory("Timeline2");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(8)
				.isEditable());

		userStoriesManager.moveUserStoryToState("Timeline", "DONE");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(9)
				.isEditable());
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
