package tests;

import model.TeamManager;
import model.TeamSettings;

import org.junit.Test;

import static org.junit.Assert.*;
import boards.UserStory;
import filtering.TargetFilter;
import filtering.chechers.TargetStateUserStoryChecker;

public class UserStoryFilteringTest {

	private TeamSettings settings = new TeamSettings();
	private TeamManager teammanager = new TeamManager(settings);

	@Test
	public void StateUserStoryFilterTest() throws Exception {
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS",
				"ACCOMPLISHED");
		teammanager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		teammanager.moveStoryToState("Timeline", "ACCOMPLISHED");
		teammanager.addUserStory("Board",
				"Voglio che ci sia un'area di testo editabile...");
		assertEquals(
				1,
				teammanager.getUserStory(
						new TargetFilter<UserStory>(new TargetStateUserStoryChecker(
								"ACCOMPLISHED"))).size());
		assertEquals("Timeline", teammanager.getUserStory(
						new TargetFilter<UserStory>(new TargetStateUserStoryChecker(
								"ACCOMPLISHED"))).get(0).toString());
	}

}
