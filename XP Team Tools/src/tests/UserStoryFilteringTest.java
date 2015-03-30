package tests;

import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;

import org.junit.Test;

import timeline.ConcreteTimeline;
import static org.junit.Assert.*;
import boards.TeamUserStoriesManager;
import boards.ConcreteUserStoriesManager;
import boards.UserStoriesManager;
import boards.UserStory;
import filtering.TargetFilter;
import filtering.chechers.StateUserStoryChecker;

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
	}

}
