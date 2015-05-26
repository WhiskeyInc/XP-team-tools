package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchTaskException;

import org.junit.Test;

import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;

public class TasksManagerTest {

	TaskManager manager = new ConcreteTaskManager();

	@Test
	public void addTaskTest() throws NameAlreadyInUseException {
		manager.addTask("Timeline", "Componente che deve...");
		assertEquals(1, manager.getTasksNumber());
	}

	@Test
	public void createdTaskIsTODO() throws Exception {
		manager.addTask("Timeline", "Componente che deve...");
		assertEquals("TODO", manager.getTask("Timeline").getState());
	}

	@Test
	public void changeTaskStateTest() throws Exception {
		manager.addTask("Timeline");
		manager.moveTaskToState("Timeline", "ACCEPTED");
		assertEquals("ACCEPTED", manager.getTask("Timeline").getState());
	}

	@Test
	public void deleteTaskTest() throws NoSuchTaskException,
			NameAlreadyInUseException {
		manager.addTask("Timeline", "Componente che deve...");
		manager.deleteTask("Timeline");
		try {
			assertEquals(null, manager.getTask("Timeline"));
		} catch (NoSuchTaskException e) {
			assertEquals(true, true);
		}
	}

	@Test
	public void displayTasksTest() throws Exception {
		manager.addTask("Timeline", "Componente che deve...");
		assertEquals("Timeline" + "Componente che deve..." + "TODO", manager
				.getTask("Timeline").toString()
				+ manager.getTask("Timeline").getDescription()
				+ manager.getTask("Timeline").getState());
	}

	@Test
	public void developersAdditionTest() throws Exception {
		manager.addTask("Timeline");
		manager.addDevelopersToTask("Timeline", "Simo", "Lele", "Ale");
		assertEquals(3, manager.getTask("Timeline").getDevelopers().size());
		assertTrue(manager.getTask("Timeline").getDevelopers().contains("Lele"));
		assertTrue(manager.getTask("Timeline").getDevelopers().contains("Simo"));
		assertTrue(manager.getTask("Timeline").getDevelopers().contains("Ale"));
	}

	@Test
	public void nameAlreadyInUseExceptionTest() {
		try {
			manager.addTask("Timeline");
		} catch (NameAlreadyInUseException e) {
			fail();
		}
		try {
			manager.addTask("Timeline");
			fail();
		} catch (NameAlreadyInUseException e) {
		}
	}

	@Test
	public void noSuchTaskExceptionTest() throws NameAlreadyInUseException {
		manager.addTask("Timeline");
		try {
			manager.deleteTask("Non esisto");
			fail();
		} catch (NoSuchTaskException e) {
		}
		try {
			manager.deleteTask("Timeline");
		} catch (NoSuchTaskException e) {
			fail();
		}

	}

}
