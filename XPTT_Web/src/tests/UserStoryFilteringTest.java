package tests;

import static org.junit.Assert.assertEquals;

import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteTeamManager;
import model.Notifier;

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
import filtering.checkers.StateUserStoryChecker;

public class UserStoryFilteringTest {

	private ConcreteProjectSettings settings = new ConcreteProjectSettings();
	private Notifier teammanager = new ConcreteTeamManager(settings,
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

	// @Test
	// public void PriorityUserStoryFilterTest() throws Exception {
	// settings.setPossibleUserStoriesPriorities("DEFAULT", "MIN",
	// "MAX");
	// userStoriesBoard.addUserStory("Timeline",
	// "Voglio che ci sia un pannello con dei tasti che...");
	// userStoriesBoard.changeStoryPriority("Timeline", "MIN");
	// userStoriesBoard.addUserStory("Board",
	// "Voglio che ci sia un'area di testo editabile...");
	// assertEquals(
	// 1,
	// userStoriesBoard
	// .getUserStories(
	// new TargetFilter<UserStory>(
	// new PriorityUserStoryChecker(
	// "MIN"))).size());
	// assertEquals(
	// "Timeline",
	// userStoriesBoard
	// .getUserStories(
	// new TargetFilter<UserStory>(
	// new PriorityUserStoryChecker(
	// "MIN"))).get(0)
	// .toString());
	// assertEquals(
	// 0,
	// userStoriesBoard
	// .getUserStories(
	// new TargetFilter<UserStory>(
	// new PriorityUserStoryChecker(
	// "MAX"))).size());
	// }

}
