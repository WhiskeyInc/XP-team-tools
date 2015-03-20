package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import boards.UserStoriesBoard;

public class UserStoriesBoardTest {

	UserStoriesBoard board = new UserStoriesBoard();

	@Test
	public void addStoryTest() {
		board.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(
				"Timeline"
						+ "Voglio che ci sia un pannello con dei tasti che...",
				board.getUserStory("Timeline").toString()
						+ board.getUserStory("Timeline").getDescription());
	}

	@Test
	public void defaultStateTest() {
		board.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals("TODO", board.getUserStory("us1").getState());
	}

	@Test
	public void setStateTest() {
		board.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		board.moveStoryToState("us1", "ACCOMPLISHED");
		assertEquals("ACCOMPLISHED", board.getUserStory("us1").getState());
	}

	@Test
	public void changePriorityTest() {
		board.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		board.addUserStory("us2", "Voglio che ci sia un menù che...");
		board.addUserStory("us3",
				"Ci deve essere un'area di testo dove poter...");
		board.changeStoryPriority("us3", 0);
		assertEquals("us3" + "us1" + "us2", board.getSortedStories().get(0)
				.toString()
				+ board.getSortedStories().get(1).toString()
				+ board.getSortedStories().get(2).toString());
		assertEquals(3, board.getSortedStories().size());
	}

	@Test
	public void taskAdditionToStory() {
		board.addUserStory("Timer",
				"Voglio che ci sia un pannello con dei tasti che...");
		board.getTasksBoard("Timer").addTask("Timeline",
				"Componente che deve...");
		assertEquals(1, board.getTasksBoard("Timer").getTasksNumber());
	}

}
