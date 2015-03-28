package tests;

import model.TeamManager;
import model.TeamSettings;

import org.junit.Test;

import static org.junit.Assert.*;
import boards.Task;
import filtering.DeveloperTaskFilter;
import filtering.StateTaskFilter;
import filtering.TargetFilter;

public class TaskFilteringTest {

	TeamSettings settings = new TeamSettings();
	TeamManager teammanager = new TeamManager(settings);

	@Test
	public void StateTaskFilterTest() throws Exception {
		settings.setPossibleTasksStates("TODO", "IN PROGRESS", "ACCOMPLISHED");
		teammanager.addUserStory("Timeline", "Voglio un pannello che...");
		teammanager.addTask("Filtro", "Timeline");
		teammanager.addTask("Bacheca", "Timeline");
		teammanager.moveTaskToState("Bacheca", "ACCOMPLISHED", "Timeline");
		assertEquals(
				1,
				teammanager.getTasks(
						"Timeline",
						new TargetFilter<Task>(new StateTaskFilter(
								"ACCOMPLISHED"))).size());
		assertEquals(
				"Bacheca",
				teammanager
						.getTasks(
								"Timeline",
								new TargetFilter<Task>(new StateTaskFilter(
										"ACCOMPLISHED"))).get(0).toString());
	}

	@Test
	public void DeveloperTaskFilterTest() throws Exception {
		settings.addTeamMember("Simone");
		settings.addTeamMember("Emanuele");
		settings.addTeamMember("Alessandro");
		teammanager.addUserStory("Timeline", "Voglio un pannello che...");
		teammanager.addTask("Filtro", "Timeline");
		teammanager.addTask("Bacheca", "Timeline");
		teammanager.addDeveloperTo("Filtro", "Simone", "Timeline");
		teammanager.addDeveloperTo("Filtro", "Alessandro", "Timeline");
		teammanager.addDeveloperTo("Filtro", "Emanuele", "Timeline");
		teammanager.addDeveloperTo("Bacheca", "Emanuele", "Timeline");
		teammanager.addDeveloperTo("Bacheca", "Simone", "Timeline");
		assertEquals(
				2,
				teammanager.getTasks(
						"Timeline",
						new TargetFilter<Task>(new DeveloperTaskFilter(
								"Emanuele", "Simone"))).size());
		assertEquals(
				"Filtro"+" Bacheca",
				teammanager.getTasks(
						"Timeline",
						new TargetFilter<Task>(new DeveloperTaskFilter(
								"Emanuele", "Simone"))).get(1).toString()+" "+teammanager.getTasks(
										"Timeline",
										new TargetFilter<Task>(new DeveloperTaskFilter(
												"Emanuele", "Simone"))).get(0).toString());
		assertEquals(
				1,
				teammanager.getTasks(
						"Timeline",
						new TargetFilter<Task>(new DeveloperTaskFilter(
								"Alessandro"))).size());
		assertEquals(
				"Filtro",
				teammanager.getTasks(
						"Timeline",
						new TargetFilter<Task>(new DeveloperTaskFilter(
								"Alessandro"))).get(0).toString());
	}
}
