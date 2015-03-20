package tests;

import static org.junit.Assert.assertEquals;
import model.BlackBoard;

import org.junit.Test;

public class BlackBoardTest {

	BlackBoard blackboard = new BlackBoard();

	@Test
	public void addnewTaskTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		assertEquals(1, blackboard.getTasksNumber());
	}

	@Test
	public void moveTaskInProgressTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		blackboard.taskInProgress("Timeline");
		assertEquals("IN PROGRESS", blackboard.getTask("Timeline").getState());
	}

	@Test
	public void moveTaskDoneTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		blackboard.taskDone("Timeline");
		assertEquals("DONE", blackboard.getTask("Timeline").getState());
	}

	@Test
	public void dropTaskTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		blackboard.dropTask("Timeline");
		assertEquals(null, blackboard.getTask("Timeline"));
	}

	@Test
	public void displayTasksTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		assertEquals("Timeline" + "Componente che deve..." + "TODO", blackboard
				.getTask("Timeline").toString()
				+ blackboard.getTask("Timeline").getDescription()
				+ blackboard.getTask("Timeline").getState());
	}

}
