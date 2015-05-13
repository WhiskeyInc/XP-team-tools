package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteTeamManager;
import model.ProjectManager;
import model.TeamComponent;
import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidPriorityException;
import model.exceptions.InvalidStateException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import util.serialization.LocalUniquenessSerializer;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.ProjectUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.ProjectTaskManager;
import boards.taskBoard.Task;
import boards.taskBoard.TaskManager;

public class TeamManagerTest {

	private ConcreteProjectSettings settings = new ConcreteProjectSettings();
	private Timeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"),
			new LocalUniquenessSerializer());
	private ProjectManager teamManager = new ConcreteTeamManager(settings,
			timeline);
	private TaskManager taskBoard = new ProjectTaskManager(
			new ConcreteTaskManager(), teamManager);
	private UserStoriesManager userStoriesBoard = new ProjectUserStoriesManager(
			new ConcreteUserStoriesManager(), teamManager);

	@Test
	public void taskAddedTest() throws Exception {
		teamManager.taskAdded(new Task("Timeline"));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Created task: Timeline", timeline.getEvent(1).toString());
	}

	@Test
	public void deletedTaskTest() throws Exception {
		teamManager.taskDeleted(new Task("Timeline"));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Deleted task: Timeline", timeline.getEvent(1).toString());
	}

	@Test
	public void taskStateChangedTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "DONE");
		teamManager.taskStateChanged(new Task("Timeline"), "TODO");
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Changed state of task Timeline. Now it is TODO", timeline
				.getEvent(1).toString());
	}

	@Test
	public void developersAddedTest() throws Exception {
		settings.setManager(teamManager);
		settings.addTeamMember(new TeamComponent("Al", "I", "Boh"),
				new TeamComponent("Lele", "F", "Tutto"), new TeamComponent(
						"Boh", "BohBoh", "niente"));
		teamManager.developersAdded(new Task("Timeline"), "Lele F", "Ale I");
		timeline.addEvent(new Event("Evento nuovo", new GregorianCalendar(2050,
				11, 15, 22, 22, 22), true));
		assertEquals(4, timeline.getEventsNumber());
		assertEquals("Developers added to task Timeline: Lele F Ale I ",
				timeline.getEvent(2).toString());
		assertEquals("Evento nuovo", timeline.getEvent(3).toString());
	}

	@Test
	public void userStoryAddedTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		teamManager.userStoryAdded(new UserStory("Timeline", taskmanager));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Created userstory: Timeline", timeline.getEvent(1)
				.toString());
	}

	@Test
	public void userStoryDeletedTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		teamManager.userStoryDeleted(new UserStory("Timeline", taskmanager));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Deleted userstory: Timeline", timeline.getEvent(1)
				.toString());
	}

	@Test
	public void priorityChangedTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		teamManager.userStoryPriorityChanged(new UserStory("Timeline",
				taskmanager), 0);
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Changed priority of userstory: Timeline: now it is 0",
				timeline.getEvent(1).toString());
	}

	@Test
	public void userStoryStateChangedTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		settings.setPossibleUserStoriesStates("TODO", "DONE");
		teamManager.userStoryStateChanged(
				new UserStory("Timeline", taskmanager), "DONE");
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Changed state of userstory Timeline: now it is DONE",
				timeline.getEvent(1).toString());
	}

	@Test
	public void tasksStateCheckTest01() throws Exception {
		taskBoard.addTask("Timeline");
		try {
			taskBoard.moveTaskToState("Timeline", "IMPLEMENTED");
			fail();
		} catch (InvalidStateException e) {
			assertTrue(true);
		}
	}

	@Test
	public void tasksStateCheckTest02() throws Exception {
		taskBoard.addTask("Timeline");
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		try {
			taskBoard.moveTaskToState("Timeline", "DONE");
		} catch (InvalidStateException e) {
			fail();
		}
	}

	@Test
	public void UserStoriesStateCheckTest01() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		userStoriesBoard.addUserStory(new UserStory("Timeline",
				"Voglio un pannello che...", taskmanager));
		try {
			userStoriesBoard.moveUserStoryToState("Timeline", "ACCOMPLISHED");
			fail();
		} catch (InvalidStateException e) {
			assertTrue(true);
		}
	}

	@Test
	public void UserStoriesStateCheckTest02() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		userStoriesBoard.addUserStory(new UserStory("Timeline",
				"Voglio un pannello che...", taskmanager));
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		try {
			userStoriesBoard.moveUserStoryToState("Timeline", "DONE");
		} catch (InvalidStateException e) {
			fail();
		}
	}

	@Test
	public void UserStoriesPriorityCheckTest01() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		userStoriesBoard.addUserStory(new UserStory("Timeline",
				"Voglio un pannello che...", taskmanager));
		try {
			userStoriesBoard.changeStoryPriority("Timeline", 11);
			fail();
		} catch (InvalidPriorityException e) {
			assertTrue(true);
		}
	}

	@Test
	public void UserStoriesPriorityCheckTest02() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		userStoriesBoard.addUserStory(new UserStory("Timeline",
				"Voglio un pannello che...", taskmanager));
		try {
			userStoriesBoard.changeStoryPriority("Timeline", 10);
		} catch (InvalidPriorityException e) {
			fail();
		}
	}

	@Test
	public void participantAdditionFailsWhenInvalidParticipant()
			throws Exception {
		settings.setManager(teamManager);
		settings.addTeamMember(new TeamComponent("Simone", "nonHoVoglia", "DP"));
		try {
			taskBoard.addDevelopersToTask("Timeline", "ziobrando");
			fail();
		} catch (InvalidMemberException e) {
			assertTrue(true);
		}
	}

}
