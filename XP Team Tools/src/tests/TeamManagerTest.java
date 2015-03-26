package tests;

import static org.junit.Assert.*;
import model.InvalidStateException;
import model.TeamManager;

import org.junit.Test;

public class TeamManagerTest {

	private TeamManager teamManager = new TeamManager();

	@Test
	public void tasksStateCheckTest01() throws Exception {
		teamManager.addTask("Timeline");
		try {
			teamManager.moveTaskToState("Timeline", "IMPLEMENTED");
			fail();
		} catch (InvalidStateException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void tasksStateCheckTest02() throws Exception {
		teamManager.addTask("Timeline");
		teamManager.setPossibleStates("TODO", "IN PROGRESS", "DONE");
		try {
			teamManager.moveTaskToState("Timeline", "DONE");
		} catch (InvalidStateException e) {
			fail();
		}
	}

}
