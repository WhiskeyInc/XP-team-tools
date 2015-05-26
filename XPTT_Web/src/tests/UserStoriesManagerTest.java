package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchUserStoryException;

import org.junit.Test;

import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import filtering.NoFilter;

public class UserStoriesManagerTest {

	UserStoriesManager manager = new ConcreteUserStoriesManager();

	@Test
	public void addStoryTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("Timeline",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		assertEquals("Timeline"
				+ "Voglio che ci sia un pannello con dei tasti che...",
				manager.getUserStory("Timeline").toString()
						+ manager.getUserStory("Timeline").getDescription());
	}

	@Test
	public void defaultStateTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		assertEquals("TODO", manager.getUserStory("us1").getState());
	}

	@Test
	public void defaultPriorityTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		assertEquals(0, manager.getUserStory("us1").getPriority());
	}

	@Test
	public void setStateTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		manager.moveUserStoryToState("us1", "ACCOMPLISHED");
		assertEquals("ACCOMPLISHED", manager.getUserStory("us1").getState());
	}

	@Test
	public void setPriorityTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("us1",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		manager.changeStoryPriority("us1", 9);
		assertEquals("us1" + 9, manager.getUserStory("us1").toString()
				+ manager.getUserStory("us1").getPriority());
	}

	@Test
	public void deleteUserStoryTest() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("Timer",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		manager.deleteUserStory("Timer");
		assertEquals(null, manager.getUserStory("Timer"));
	}

	@Test
	public void nameAlreadyInUseExceptionTest() {
		TaskManager taskmanager = new ConcreteTaskManager();
		try {
			manager.addUserStory(new UserStory("Timeline",
					"Voglio una timeline che...", taskmanager));
		} catch (NameAlreadyInUseException e) {
			fail();
		}
		try {
			manager.addUserStory(new UserStory("Timeline",
					"Voglio una timeline che...", taskmanager));
			fail();
		} catch (NameAlreadyInUseException e) {
			assertEquals(1, 1);
		}
	}

	@Test
	public void noSuchUserStoryTest() throws NameAlreadyInUseException {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("Timeline",
				"Voglio una timeline che...", taskmanager));
		try {
			manager.deleteUserStory("Non esisto");
			fail();
		} catch (NoSuchUserStoryException e) {
		}
		try {
			manager.deleteUserStory("Timeline");
		} catch (NoSuchUserStoryException e) {
			fail();
		}
	}

	@Test
	public void taskAdditionToStory() throws Exception {
		TaskManager taskmanager = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("Timer",
				"Voglio che ci sia un pannello con dei tasti che...",
				taskmanager));
		manager.getUserStory("Timer").addTask("Timeline",
				"Componente che deve...");
		assertEquals(1, manager.getUserStory("Timer").getTasksNumber());
	}

	@Test
	public void orderUserStoriesTest() throws Exception {
		TaskManager taskmanager1 = new ConcreteTaskManager();
		TaskManager taskmanager2 = new ConcreteTaskManager();
		TaskManager taskmanager3 = new ConcreteTaskManager();
		TaskManager taskmanager4 = new ConcreteTaskManager();
		manager.addUserStory(new UserStory("StoriaA", "DescrizioneA",
				taskmanager1));
		manager.addUserStory(new UserStory("StoriaB", "DescrizioneB",
				taskmanager2));
		manager.addUserStory(new UserStory("StoriaC", "DescrizioneC",
				taskmanager3));
		manager.addUserStory(new UserStory("StoriaD", "DescrizioneD",
				taskmanager4));
		manager.changeStoryPriority("StoriaA", 2);
		manager.changeStoryPriority("StoriaB", 9);
		manager.changeStoryPriority("StoriaC", 5);
		manager.changeStoryPriority("StoriaD", 2);
		assertEquals(
				"StoriaB" + "StoriaC" + "StoriaD" + "StoriaA",
				manager.getUserStories(new NoFilter<UserStory>()).get(0)
						.toString()
						+ manager.getUserStories(new NoFilter<UserStory>())
								.get(1).toString()
						+ manager.getUserStories(new NoFilter<UserStory>())
								.get(2).toString()
						+ manager.getUserStories(new NoFilter<UserStory>())
								.get(3).toString());
	}

}
