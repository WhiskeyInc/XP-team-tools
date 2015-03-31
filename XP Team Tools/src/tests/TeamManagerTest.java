package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.TeamManager;
import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.exceptions.InvalidDateException;
import model.exceptions.InvalidStateException;
import model.exceptions.UnmovableEventException;

import org.junit.Test;

import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import boards.taskBoard.TeamTaskManager;
import timeline.Event;
import timeline.ConcreteTimeline;
import timeline.Timeline;
import filtering.NoFilter;

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
	public void movableEventTest() throws Exception{
		
		settings.setPossibleTasksStates("TODO", "DONE");
		settings.setPossibleUserStoriesStates("TODO", "DONE");
		settings.addTeamMember("Lele", "Simo");
		
		Event creation = timeline.getEvent("creation");
		assertTrue(!creation.isMovable());
		
		userStoriesBoard.addUserStory("Timeline", "Voglio un pannello che...");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(1).isMovable());
		
		taskBoard.addTask("Dormire");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(2).isMovable());
		
		taskBoard.addTask("Dormire2", "Dormire tanto perchè cambia l'ora");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(3).isMovable());
		
		taskBoard.deleteTask("Dormire2");		
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(4).isMovable());
		
		taskBoard.moveTaskToState("Dormire", "TODO");		
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(5).isMovable());
		
		taskBoard.addDevelopersToTask("Dormire", "Lele", "Simo");		
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(6).isMovable());
				
		userStoriesBoard.addUserStory("Timeline2", null);
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(7).isMovable());
		
		userStoriesBoard.deleteUserStory("Timeline2");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(8).isMovable());
		
		userStoriesBoard.moveUserStoryToState("Timeline", "DONE");
		assertTrue(!timeline.getEvents(new NoFilter<Event>()).get(9).isMovable());
		
	}
	
	@Test
	public void addEventTest() throws Exception {
		GregorianCalendar date = new GregorianCalendar(2015, 04, 12, 12, 12, 12);
		timeline.addEvent(new Event("Release", date));
		assertEquals(2, timeline.getEventsNumber());
		assertEquals("Release", timeline.getEvent("Release").toString());
		assertEquals(date, timeline.getEvent("Release").getDate());
	}
	
	@Test
	public void changeDateTest() throws InvalidDateException {
		GregorianCalendar date = new GregorianCalendar(2015, 04, 12, 12, 12, 12);
		timeline.addEvent(new Event("Release", date));
		GregorianCalendar newDate = new GregorianCalendar(2014, 05, 12, 12, 12, 12);
		try {
			timeline.moveEvent("Release", newDate);
		} catch (UnmovableEventException e) {
			fail();
		}
		assertEquals(newDate, timeline.getEvent("Release").getDate());
		try {
			timeline.moveEvent("creation", newDate);
			fail();
		} catch (UnmovableEventException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void dropEventTest() throws Exception {
		GregorianCalendar date = new GregorianCalendar(2015, 04, 12, 12, 12, 12);
		timeline.addEvent(new Event("Release", date));
		timeline.deleteEvent("Release");
		assertEquals(1, timeline.getEventsNumber());
	}
	
	
}
