package tests.JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.model.Team;
import server.model.services.chat.ChatsManager;
import server.model.services.teams.TeamsManager;

public class TeamsManagerTest {


	private TeamsManager teamsManager;
	
	@Before
	public void setUp() {
		teamsManager = TeamsManager.getInstance();
	}
	
	@Test
	public void addTest() {
		teamsManager.removeAll();
		Team team = new Team("Test");
		teamsManager.add(team);
		assertEquals(1, teamsManager.size());
		Team team1 = new Team("Test");
		teamsManager.add(team1);
		assertEquals(1, teamsManager.size());
		Team team2 = new Team("Test");
		team2.addMember("1");
		teamsManager.add(team2);
		assertEquals(1, teamsManager.size());
	}
	
	@Test
	public void hasTest() throws Exception {
		teamsManager.removeAll();
		Team team = new Team("Test");
		teamsManager.add(team);
		assertTrue(teamsManager.has("Test"));
		assertFalse(teamsManager.has("Prova"));
		
	}
	
	@Test
	public void getTest() throws Exception {
		teamsManager.removeAll();
		Team team = new Team("Test");
		teamsManager.add(team);
		int index = teamsManager.indexOf("Test");
		assertEquals(0, index);
		assertEquals(team, teamsManager.get(index));
	}

}
