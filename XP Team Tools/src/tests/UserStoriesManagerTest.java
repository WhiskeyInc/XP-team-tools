package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;

import org.junit.Test;

import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import filtering.NoFilter;

public class UserStoriesManagerTest {

	UserStoriesManager manager = new ConcreteUserStoriesManager();

	@Test
	public void addStoryTest() throws Exception {
		manager.addUserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals("Timeline"
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
	public void defaultPriorityTest() throws Exception {
		manager.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		assertEquals(0, manager.getUserStory("us1").getPriority());
	}

	@Test
	public void setStateTest() throws Exception {
		manager.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.moveUserStoryToState("us1", "ACCOMPLISHED");
		assertEquals("ACCOMPLISHED", manager.getUserStory("us1").getState());
	}

	@Test
	public void setPriorityTest() throws Exception {
		manager.addUserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.changeStoryPriority("us1", 9);
		assertEquals("us1" + 9, manager.getUserStory("us1").toString()
				+ manager.getUserStory("us1").getPriority());
	}

	@Test
	public void deleteUserStoryTest() throws Exception {
		manager.addUserStory("Timer",
				"Voglio che ci sia un pannello con dei tasti che...");
		manager.deleteUserStory("Timer");
		assertEquals(null, manager.getUserStory("Timer"));
	}

	@Test
	public void nameAlreadyInUseExceptionTest() {
		try {
			manager.addUserStory("Timeline", "Voglio una timeline che...");
		} catch (NameAlreadyInUseException e) {
			fail();
		}
		try {
			manager.addUserStory("Timeline", "Voglio una timeline che...");
			fail();
		} catch (NameAlreadyInUseException e) {
			assertEquals(1, 1);
		}
	}

	@Test
	public void noSuchUserStoryTest() throws NameAlreadyInUseException {
		manager.addUserStory("Timeline", "Voglio una timeline che...");
		try {
			manager.deleteUserStory("Non esisto");
			fail();
		} catch (NoSuchUserStoryException e) {
			assertEquals(1, 1);
		}
		try {
			manager.deleteUserStory("Timeline");
		} catch (NoSuchUserStoryException e) {
			fail();
		}
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
	public void orderUserStoriesTest() throws Exception {
		manager.addUserStory("StoriaA", "DescrizioneA");
		manager.addUserStory("StoriaB", "DescrizioneB");
		manager.addUserStory("StoriaC", "DescrizioneC");
		manager.addUserStory("StoriaD", "DescrizioneD");
		manager.changeStoryPriority("StoriaA", 2);
		manager.changeStoryPriority("StoriaB", 9);
		manager.changeStoryPriority("StoriaC", 5);
		manager.changeStoryPriority("StoriaD", 2);
		assertEquals("StoriaB" + "StoriaC" + "StoriaD" + "StoriaA", manager
				.getUserStories(new NoFilter<UserStory>()).get(0).toString()
				+ manager.getUserStories(new NoFilter<UserStory>()).get(1).toString()
				+ manager.getUserStories(new NoFilter<UserStory>()).get(2).toString()
				+ manager.getUserStories(new NoFilter<UserStory>()).get(3).toString());
	}

}
