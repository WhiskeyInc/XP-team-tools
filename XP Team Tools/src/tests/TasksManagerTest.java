package tests;

import static org.junit.Assert.assertEquals;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchTaskException;

import org.junit.Test;

import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;

public class TasksManagerTest {

	TaskManager manager = new ConcreteTaskManager();

	@Test
	public void newTaskTest() throws Exception {
		manager.addTask("Timeline", "Componente che deve...");
		assertEquals(1, manager.getTasksNumber());
	}

	@Test
	public void taskCreatedIsTODO() throws Exception {
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
	public void dropTaskTest() throws NoSuchTaskException, NameAlreadyInUseException {
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
	
	/* This test is outdated! Filtering according to state has been moved to TeamManager
	 * 
	 */
//	@Test
//	public void findTasksByState() throws Exception {
//		ArrayList<String> tasks = new ArrayList<String>();
//		manager.addTask("Timeline");
//		manager.addTask("Board");
//		manager.addTask("Chat");
//		manager.moveTaskToState("Timeline", "IN PROGRESS");
//		tasks.add("Timeline");
//		ArrayList<String> inProgressTasks = new ArrayList<String>();
//		for (Task task : manager.getTasks("IN PROGRESS")) {
//			inProgressTasks.add(task.toString());
//		}
//		assertEquals(tasks, inProgressTasks);
//	}
}
