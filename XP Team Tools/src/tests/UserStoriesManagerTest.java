package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import boards.UserStoriesManager;

public class UserStoriesManagerTest {

	UserStoriesManager manager = new UserStoriesManager();

	@Test
	public void addStoryTest() throws Exception {
		manager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(
				"Timeline"
						+ "Voglio che ci sia un pannello con dei tasti che...",
				manager.getUserStory("Timeline").toString()
						+ manager.getUserStory("Timeline").getDescription());
	}

	@Test
	public void defaultStateTest() throws Exception {
		manager.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals("TODO", manager.getUserStory("us1").getState());
	}

	@Test
	public void setStateTest() throws Exception {
		manager.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.moveStoryToState("us1", "ACCOMPLISHED");
		assertEquals("ACCOMPLISHED", manager.getUserStory("us1").getState());
	}

	@Test
	public void changePriorityTest() throws Exception {
		manager.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.addUserStory("us2", "Voglio che ci sia un menù che...");
		manager.addUserStory("us3",
				"Ci deve essere un'area di testo dove poter...");
		manager.changeStoryPriority("us3", 0);
		assertEquals("us3" + "us1" + "us2", manager.getSortedStories().get(0)
				.toString()
				+ manager.getSortedStories().get(1).toString()
				+ manager.getSortedStories().get(2).toString());
		assertEquals(3, manager.getSortedStories().size());
	}

	@Test
	public void taskAdditionToStory() throws Exception {
		manager.addUserStory("Timer",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.getUserStory("Timer").addTask("Timeline",
				"Componente che deve...");
		assertEquals(1, manager.getUserStory("Timer").getTasksNumber());
	}

	@Test
	public void deleteUserStoryTest() throws Exception{
		manager.addUserStory("Timer",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.deleteUserStory("Timer");
		assertEquals(null, manager.getUserStory("Timer"));
	}
}
