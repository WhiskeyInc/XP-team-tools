package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;
import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidStateException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.Task;
import boards.taskBoard.TaskManager;
import boards.taskBoard.TeamTaskManager;
import filtering.NoFilter;

public class TeamManagerTest {

	private ConcreteTeamSettings settings = new ConcreteTeamSettings();
	private Timeline timeline = new ConcreteTimeline();
	private TeamManager teamManager = new ConcreteTeamManager(settings,
			timeline);
	private TaskManager taskBoard = new TeamTaskManager(
			new ConcreteTaskManager(), teamManager);
	private UserStoriesManager userStoriesBoard = new TeamUserStoriesManager(
			new ConcreteUserStoriesManager(), teamManager);

	@Test
	public void taskAddedTest() throws Exception {
		teamManager.taskAdded(new Task("Timeline"));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Created task: Timeline",
				timeline.getEvent("Created task: Timeline").toString());
	}

	@Test
	public void deletedTaskTest() throws Exception {
		teamManager.taskDeleted(new Task("Timeline"));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Deleted task: Timeline",
				timeline.getEvent("Deleted task: Timeline").toString());
	}

	@Test
	public void taskStateChangedTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "DONE");
		teamManager.taskStateChanged(new Task("Timeline"), "TODO");
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Changed state of task Timeline. Now it is TODO", timeline
				.getEvent("Changed state of task Timeline. Now it is TODO")
				.toString());
	}
	
	@Test
	public void developersAddedTest() throws Exception {
		settings.addTeamMember("Simo", "Lele", "Ale", "Andre");
		teamManager.developersAdded(new Task("Timeline"), "Lele", "Ale");
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Developers added to task Timeline: Lele Ale ", timeline.getEvent("Developers added to task Timeline: Lele Ale ").toString());
	}
	
	@Test
	public void userStoryAddedTest() throws Exception {
		teamManager.userStoryAdded(new UserStory("Timeline"));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Created userstory: Timeline", timeline.getEvent("Created userstory: Timeline").toString());
		}
	
	@Test
	public void userStoryDeletedTest() throws Exception {
		teamManager.userStoryDeleted(new UserStory("Timeline"));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Deleted userstory: Timeline", timeline.getEvent("Deleted userstory: Timeline").toString());
	}

	@Test
	public void priorityChangedTest() throws Exception {
		teamManager.userStoryPriorityChanged(new UserStory("Timeline"), "DEFAULT");
		assertEquals(2, timeline.getEventsNumber());
		System.err.println(timeline.getEvents(new NoFilter<Event>()).get(1).toString());
		assertEquals("Changed priority of userstory: Timeline: now it is DEFAULT", timeline.getEvent("Changed priority of userstory: Timeline: now it is DEFAULT").toString());
	}
	
	@Test
	public void userStoryStateChangedTest() throws Exception {
		settings.setPossibleUserStoriesStates("TODO", "DONE");
		teamManager.userStoryStateChanged(new UserStory("Timeline"), "DONE");
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Changed state of userstory Timeline: now it is DONE", timeline.getEvent("Changed state of userstory Timeline: now it is DONE").toString());
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
		userStoriesBoard.addUserStory("Timeline", "Voglio un pannello che...");
		try {
			userStoriesBoard.moveUserStoryToState("Timeline", "ACCOMPLISHED");
			fail();
		} catch (InvalidStateException e) {
			assertTrue(true);
		}
	}

	@Test
	public void UserStoriesStateCheckTest02() throws Exception {
		userStoriesBoard.addUserStory("Timeline", "Voglio un pannello che...");
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		try {
			userStoriesBoard.moveUserStoryToState("Timeline", "DONE");
		} catch (InvalidStateException e) {
			fail();
		}
	}

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

}
