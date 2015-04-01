package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;
import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidStateException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Timeline;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import boards.taskBoard.TeamTaskManager;

public class TeamManagerTest {

	private ConcreteTeamSettings settings = new ConcreteTeamSettings();
	private Timeline timeline = new ConcreteTimeline();
	private TeamManager teamManager = new ConcreteTeamManager(settings, timeline);
	private TaskManager taskBoard = new TeamTaskManager(new ConcreteTaskManager(),
			teamManager);
	private UserStoriesManager userStoriesBoard = new TeamUserStoriesManager(
			new ConcreteUserStoriesManager(), teamManager);


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
