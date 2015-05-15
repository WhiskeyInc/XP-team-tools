package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.model.Team;

public class TeamTest {

	@Test
	public void basicTeamTest() {
		String member1 = "io";
		Team team = new Team("Prova");
		team.addMember(member1);
		
		assertEquals(1, team.getMembers().length);
		assertEquals(member1, team.getMembers()[0]);
		
		
		String member2 = "tu";
		team.addMember(member2);
		
		assertEquals(2, team.getMembers().length);
		assertEquals(member2, team.getMembers()[1]);
		
		team = new Team("LeFere");
		assertEquals("LeFere", team.getName());
		team.addMember("Alb");
		team.addMember("Alb");
		String[] st = team.getMembers();
		for (int i = 0; i < st.length; i++) {
			assertEquals("Alb", st[i]);

		}
		assertEquals(1, st.length);
		team.addMember("Fra");
		String[] vet1 = team.getMembers();
		String name = vet1[0];
		assertEquals("Alb", name);
		String name1 = vet1[1];
		assertEquals("Fra", name1);
		assertEquals(2,vet1.length);
		
		team.remove(name);
		team.remove(name1);
		String vet[] = team.getMembers();
		
		assertEquals(0, vet.length);
	}
	
	@Test
	public void orderTeamTest() {
		String member1 = "tu";

		Team team = new Team("Prova");
		team.addMember(member1);
		
		assertEquals(1, team.getMembers().length);
		assertEquals(member1, team.getMembers()[0]);
		
		String member2 = "io";

		team.addMember(member2);
		
		assertEquals(2, team.getMembers().length);
		assertEquals(member2, team.getMembers()[0]);
		assertEquals(member1, team.getMembers()[1]);

	}
	
	@Test
	public void hasTest() throws Exception {
		
		String member1 = "io";
		Team team = new Team("Prova");
		team.addMember(member1);
		
		assertTrue(team.hasMember(member1));
		assertFalse(team.hasMember("due"));
		
		String member2 = "tu";

		team.addMember(member2);
		assertTrue(team.hasMember(member1));
		assertTrue(team.hasMember(member2));
		
		team.addMember(member2);
		assertTrue(team.hasMember(member1));
		assertTrue(team.hasMember(member2));
		assertEquals(2, team.getMembers().length);
		
		
	}

}
