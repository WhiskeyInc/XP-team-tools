package tests;

import static org.junit.Assert.assertEquals;

import java.util.TimeZone;

import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.Member;
import model.TeamManager;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Timeline;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.Task;
import boards.taskBoard.TaskManager;
import boards.taskBoard.TeamTaskManager;
import filtering.TargetFilter;
import filtering.checkers.DevelopersTaskChecker;
import filtering.checkers.StateTaskChecker;

public class TaskFilteringTest {

	ConcreteTeamSettings settings = new ConcreteTeamSettings();
	ConcreteTaskManager taskBoard = new ConcreteTaskManager();
	Timeline timeline = new ConcreteTimeline(TimeZone.getTimeZone("Europe/Rome"));
	UserStoriesManager userStoriesBoard = new ConcreteUserStoriesManager();
	TeamManager manager = new ConcreteTeamManager(settings, timeline);
	UserStoriesManager userStoriesManager = new TeamUserStoriesManager(
			userStoriesBoard, manager);
	TaskManager teamTaskManager = new TeamTaskManager(taskBoard, manager);

	@Test
	public void StateTaskFilterTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "ACCOMPLISHED");
		teamTaskManager.addTask("Timeline");
		teamTaskManager.addTask("Filtro");
		teamTaskManager.addTask("Bacheca");
		teamTaskManager.moveTaskToState("Bacheca", "ACCOMPLISHED");
		assertEquals(
				1,
				teamTaskManager.getTasks(
						new TargetFilter<Task>(new StateTaskChecker(
								"ACCOMPLISHED"))).size());
		assertEquals(
				"Bacheca",
				teamTaskManager
						.getTasks(
								new TargetFilter<Task>(new StateTaskChecker(
										"ACCOMPLISHED"))).get(0).toString());
		assertEquals(
				0,
				teamTaskManager.getTasks(
						new TargetFilter<Task>(new StateTaskChecker(
								"IN PROGRESS"))).size());
		assertEquals(
				2,
				teamTaskManager.getTasks(
						new TargetFilter<Task>(new StateTaskChecker("TODO")))
						.size());
	}

	@Test
	public void DeveloperTaskFilterTest() throws Exception {
		settings.setManager(manager);
		settings.addTeamMember(new Member("Simone", "C", "DP"));
		settings.addTeamMember(new Member("Lele", "Fabs", "Tester"), new Member("Al", "Incre", "OS"));
		teamTaskManager.addTask("Filtro");
		teamTaskManager.addTask("Bacheca");
		teamTaskManager.addTask("Nessun Partecipante");
		teamTaskManager.addDevelopersToTask("Filtro", "Simone C");
		teamTaskManager.addDevelopersToTask("Filtro", "Lele Fabs");
		teamTaskManager.addDevelopersToTask("Filtro", "Al Incre");
		teamTaskManager.addDevelopersToTask("Bacheca", "Simone C", "Lele Fabs");
		teamTaskManager.addDevelopersToTask("Bacheca", "Simone C");
		assertEquals(
				2,
				teamTaskManager.getTasks(
						new TargetFilter<Task>(new DevelopersTaskChecker(
								"Lele Fabs", "Simone C"))).size());
		assertEquals(
				"Filtro" + "Bacheca",
				teamTaskManager
						.getTasks(
								new TargetFilter<Task>(
										new DevelopersTaskChecker("Lele Fabs",
												"Simone C"))).get(1).toString()
						+ teamTaskManager
								.getTasks(
										new TargetFilter<Task>(
												new DevelopersTaskChecker(
														"Lele Fabs", "Simone C")))
								.get(0).toString());
		assertEquals(
				1,
				teamTaskManager.getTasks(
						new TargetFilter<Task>(new DevelopersTaskChecker(
								"Al Incre"))).size());
		assertEquals(
				"Filtro",
				teamTaskManager
						.getTasks(
								new TargetFilter<Task>(
										new DevelopersTaskChecker("Al Incre")))
						.get(0).toString());
	}
}
