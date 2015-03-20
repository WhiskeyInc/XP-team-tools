package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import boards.Task;
import boards.TasksBoard;

public class TasksBoardTest {

	TasksBoard board = new TasksBoard();

	@Test
	public void newTaskTest() {
		board.addTask("Timeline", "Componente che deve...");
		assertEquals(1, board.getTasksNumber());
	}

	@Test
	public void taskCreatedIsTODO() {
		board.addTask("Timeline", "Componente che deve...");
		assertEquals("TODO", board.getTask("Timeline").getState());
	}
	
	@Test
	public void changeTaskStateTest() throws Exception {
		board.addTask("Timeline");
		board.moveTaskToState("Timeline", "ACCEPTED");
		assertEquals("ACCEPTED", board.getTask("Timeline").getState());
	}


	@Test
	public void dropTaskTest() {
		board.addTask("Timeline", "Componente che deve...");
		board.deleteTask("Timeline");
		assertEquals(null, board.getTask("Timeline"));
	}

	@Test
	public void displayTasksTest() {
		board.addTask("Timeline", "Componente che deve...");
		assertEquals("Timeline" + "Componente che deve..." + "TODO", board
				.getTask("Timeline").toString()
				+ board.getTask("Timeline").getDescription()
				+ board.getTask("Timeline").getState());
	}
	
	@Test
	public void findTasksByState() {
		ArrayList<String> tasks = new ArrayList<String>();
		board.addTask("Timeline");
		board.addTask("Board");
		board.addTask("Chat");
		board.moveTaskToState("Timeline", "IN PROGRESS");
		tasks.add("Timeline");
		ArrayList<String> inProgressTasks = new ArrayList<String>();
		for (Task task : board.getTasks("IN PROGRESS")) {
			inProgressTasks.add(task.toString());
		}
		assertEquals(tasks, inProgressTasks);
	}
}
