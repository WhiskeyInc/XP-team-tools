package tests;

import static org.junit.Assert.assertEquals;

import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteProjectManager;
import model.ProjectManager;
import model.TeamComponent;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Timeline;
import util.serialization.LocalIdentifiabilitySerializer;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.ProjectUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.ProjectTaskManager;
import boards.taskBoard.Task;
import boards.taskBoard.TaskManager;
import filtering.TargetFilter;
import filtering.checkers.DevelopersTaskChecker;
import filtering.checkers.StateTaskChecker;

public class TaskFilteringTest {

	ConcreteProjectSettings settings = new ConcreteProjectSettings();
	ConcreteTaskManager taskBoard = new ConcreteTaskManager();
	Timeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"),
			new LocalIdentifiabilitySerializer());
	UserStoriesManager userStoriesBoard = new ConcreteUserStoriesManager();
	ProjectManager manager = new ConcreteProjectManager(settings, timeline);
	UserStoriesManager userStoriesManager = new ProjectUserStoriesManager(
			userStoriesBoard, manager);
	TaskManager teamTaskManager = new ProjectTaskManager(taskBoard, manager);

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
		settings.addTeamMember(new TeamComponent("Simone", "C", "DP"));
		settings.addTeamMember(new TeamComponent("Lele", "Fabs", "Tester"),
				new TeamComponent("Al", "Incre", "OS"));
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
