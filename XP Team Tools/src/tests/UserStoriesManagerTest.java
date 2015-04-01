package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;

import org.junit.Test;

import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;

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
		assertEquals("DEFAULT", manager.getUserStory("us1").getPriority());
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
		manager.changeStoryPriority("us1", "MAX");
		assertEquals("us1" + "MAX", manager.getUserStory("us1").toString()
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

}
