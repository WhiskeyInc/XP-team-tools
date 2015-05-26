package tests;

import static org.junit.Assert.*;

import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteProjectManager;
import model.ProjectManager;

import org.junit.Test;

import timeline.ConcreteTimeline;
import util.serialization.LocalIdentifiabilitySerializer;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.ProjectUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import filtering.TargetFilter;
import filtering.checkers.NameUserStoryChecker;
import filtering.checkers.StateUserStoryChecker;

public class UserStoryFilteringTest {

	private ConcreteProjectSettings settings = new ConcreteProjectSettings();
	private ProjectManager teammanager = new ConcreteProjectManager(settings,
			new ConcreteTimeline(TimeZone.getTimeZone("Europe/Rome"),
					new LocalIdentifiabilitySerializer()));
	private UserStoriesManager userStoriesBoard = new ProjectUserStoriesManager(
			new ConcreteUserStoriesManager(), teammanager);

	@Test
	public void StateUserStoryFilterTest() throws Exception {
		TaskManager taskmanager1 = new ConcreteTaskManager();
		TaskManager taskmanager2 = new ConcreteTaskManager();
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS",
				"ACCOMPLISHED");
		userStoriesBoard.addUserStory(new UserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager1));
		userStoriesBoard.moveUserStoryToState("Timeline", "ACCOMPLISHED");
		userStoriesBoard
				.addUserStory(new UserStory("Board",
						"Voglio che ci sia un'area di testo editabile...",
						taskmanager2));
		assertEquals(
				1,
				userStoriesBoard.getUserStories(
						new TargetFilter<UserStory>(new StateUserStoryChecker(
								"ACCOMPLISHED"))).size());
		assertEquals(
				"Timeline",
				userStoriesBoard
						.getUserStories(
								new TargetFilter<UserStory>(
										new StateUserStoryChecker(
												"ACCOMPLISHED"))).get(0)
						.toString());
		assertEquals(
				0,
				userStoriesBoard.getUserStories(
						new TargetFilter<UserStory>(new StateUserStoryChecker(
								"IN PROGRESS"))).size());
	}

	@Test
	public void nameUserStoryTest() throws Exception {
		TaskManager taskmanager1 = new ConcreteTaskManager();
		TaskManager taskmanager2 = new ConcreteTaskManager();
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS",
				"ACCOMPLISHED");
		userStoriesBoard.addUserStory(new UserStory("Timeline test",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager1));
		userStoriesBoard
				.addUserStory(new UserStory("Board test",
						"Voglio che ci sia un'area di testo editabile...",
						taskmanager2));
		assertEquals(2, userStoriesBoard.getUserStories(new TargetFilter<UserStory>(
				new NameUserStoryChecker("test"))).size());
		assertEquals(1, userStoriesBoard.getUserStories(new TargetFilter<UserStory>(
				new NameUserStoryChecker("Board"))).size());
		assertEquals(0, userStoriesBoard.getUserStories(new TargetFilter<UserStory>(
				new NameUserStoryChecker("cdbhuscbidshciscsdicidc"))).size());
	}

}
