package tests;

import static org.junit.Assert.*;
import model.BlackBoard;

import org.junit.Test;

public class BlackBoardTest {

	BlackBoard blackboard = new BlackBoard();

	@Test
	public void bbaddnewTaskTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		assertEquals(1, blackboard.getTasks().size());
	}

	@Test
	public void bbmoveTaskInProgressTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		blackboard.moveTaskInProgress("Timeline");
		assertEquals("IN PROGRESS", blackboard.getTasks().get("Timeline")
				.getState());
	}

	@Test
	public void bbmoveTaskDoneTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		blackboard.moveTaskDone("Timeline");
		assertEquals("DONE", blackboard.getTasks().get("Timeline").getState());
	}

	@Test
	public void bbdropTaskTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		blackboard.dropTask("Timeline");
		assertEquals(null, blackboard.getTasks().get("Timeline"));
	}

	@Test
	public void bbdisplayTasksTest() {
		blackboard.addNewTask("Timeline", "Componente che deve...");
		assertEquals("Timeline" + "Componente che deve..." + "TODO", blackboard
				.getTasks().get("Timeline").toString()
				+ blackboard.getTasks().get("Timeline").getDescription()
				+ blackboard.getTasks().get("Timeline").getState());
	}

}
