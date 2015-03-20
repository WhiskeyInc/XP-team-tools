package tests;

import static org.junit.Assert.*;
import model.BlackBoard;

import org.junit.Test;

public class BlackBoardTest {

	BlackBoard blackboard = new BlackBoard();

	@Test
	public void addnewTaskTest() {
		blackboard.addTask("Timeline", "Componente che deve...");
		assertEquals(1, blackboard.getTasksNumber());
	}

	@Test
	public void taskCreatedIsTODO() {
		blackboard.addTask("Timeline", "Componente che deve...");
		assertEquals("TODO", blackboard.getTask("Timeline").getState());
	}
	
	@Test
	public void changeTaskState() throws Exception {
		blackboard.addTask("Timeline");
		blackboard.moveTaskToState("Timeline", "ACCEPTED");
		assertEquals("ACCEPTED", blackboard.getTask("Timeline").getState());
	}


	@Test
	public void dropTaskTest() {
		blackboard.addTask("Timeline", "Componente che deve...");
		blackboard.deleteTask("Timeline");
		assertEquals(null, blackboard.getTask("Timeline"));
	}

	@Test
	public void displayTasksTest() {
		blackboard.addTask("Timeline", "Componente che deve...");
		assertEquals("Timeline" + "Componente che deve..." + "TODO", blackboard
				.getTask("Timeline").toString()
				+ blackboard.getTask("Timeline").getDescription()
				+ blackboard.getTask("Timeline").getState());
	}
}
