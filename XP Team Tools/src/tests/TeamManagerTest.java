package tests;

import static org.junit.Assert.*;
import model.InvalidStateException;
import model.TeamManager;
import model.TeamSettings;

import org.junit.Test;

public class TeamManagerTest {

	private TeamSettings settings = new TeamSettings();
	private TeamManager teamManager = new TeamManager(settings);

	@Test
	public void tasksStateCheckTest01() throws Exception {
		teamManager.addTask("Timeline", "GENERAL");
		try {
			teamManager.moveTaskToState("Timeline", "IMPLEMENTED", "GENERAL");
			fail();
		} catch (InvalidStateException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void tasksStateCheckTest02() throws Exception {
		teamManager.addTask("Timeline", "GENERAL");
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "DONE");
		try {
			teamManager.moveTaskToState("Timeline", "DONE", "GENERAL");
		} catch (InvalidStateException e) {
			fail();
		}
	}

	@Test
	public void UserStoriesStateCheckTest01() throws Exception {
		teamManager.addUserStory("Timeline", "Voglio un pannello che...");
		try {
			teamManager.moveStoryToState("Timeline", "ACCOMPLISHED");
			fail();
		} catch (InvalidStateException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void UserStoriesStateCheckTest02() throws Exception {
		teamManager.addUserStory("Timeline", "Voglio un pannello che...");
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		try {
			teamManager.moveStoryToState("Timeline", "DONE");
		} catch (InvalidStateException e) {
			fail();
		}
	}
	
}
