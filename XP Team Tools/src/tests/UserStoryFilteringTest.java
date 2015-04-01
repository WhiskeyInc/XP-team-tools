package tests;

import static org.junit.Assert.assertEquals;
import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;

import org.junit.Test;

import timeline.ConcreteTimeline;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.TeamUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import filtering.TargetFilter;
import filtering.checkers.StateUserStoryChecker;

public class UserStoryFilteringTest {

	private ConcreteTeamSettings settings = new ConcreteTeamSettings();
	private TeamManager teammanager = new ConcreteTeamManager(settings,
			new ConcreteTimeline());
	private UserStoriesManager userStoriesBoard = new TeamUserStoriesManager(
			new ConcreteUserStoriesManager(), teammanager);

	@Test
	public void StateUserStoryFilterTest() throws Exception {
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS",
				"ACCOMPLISHED");
		userStoriesBoard.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		userStoriesBoard.moveUserStoryToState("Timeline", "ACCOMPLISHED");
		userStoriesBoard.addUserStory("Board",
				"Voglio che ci sia un'area di testo editabile...");
		assertEquals(
				1,
				userStoriesBoard
						.getUserStories(
								new TargetFilter<UserStory>(
										new StateUserStoryChecker(
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
				userStoriesBoard
						.getUserStories(
								new TargetFilter<UserStory>(
										new StateUserStoryChecker(
												"IN PROGRESS"))).size());
	}

}
