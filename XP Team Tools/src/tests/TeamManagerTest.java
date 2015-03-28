package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.InvalidStateException;
import model.TeamManager;
import model.TeamSettings;

import org.junit.Test;

import timeline.Event;
import filtering.NoFilter;

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
	
	@Test
	public void movableEventTest() throws Exception{
		
		settings.setPossibleTasksStates("TODO", "DONE");
		settings.setPossibleUserStoriesStates("TODO", "DONE");
		settings.addTeamMember("Lele");
		
		Event creation = teamManager.getEvent("creation");
		assertTrue(!creation.isMovable());
		
		teamManager.addUserStory("Timeline", "Voglio un pannello che...");
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(1).isMovable());
		
		teamManager.addTask("Dormire", "Timeline");
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(2).isMovable());
		
		teamManager.addTask("Dormire2", "Dormire tanto perchè cambia l'ora", "Timeline");
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(3).isMovable());
		
		teamManager.deleteTask("Dormire2", "Timeline");		
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(4).isMovable());
		
		teamManager.moveTaskToState("Dormire", "TODO", "Timeline");		
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(5).isMovable());
		
		teamManager.addDeveloperToTask("Dormire", "Lele", "Timeline");		
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(6).isMovable());
				
		teamManager.addUserStory("Timeline2");
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(7).isMovable());
		
		teamManager.deleteUserStory("Timeline2");
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(8).isMovable());
		
		teamManager.moveStoryToState("Timeline", "DONE");
		assertTrue(!teamManager.getEvents(new NoFilter<Event>()).get(9).isMovable());
		
	}
}
