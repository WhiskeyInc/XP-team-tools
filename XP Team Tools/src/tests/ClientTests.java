package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import client.model.ClientDetails;
import client.model.Team;

public class ClientTests {

	@Test
	public void ClientDetailsTest() throws Exception {
		String nickname = "Alb";
		ClientDetails detail = new ClientDetails(nickname, "TeamName");
		assertEquals("Alb", detail.getNickname());
		
	}
	
	@Test
	public void TeamTest() {
		
		Team team = new Team("LeFere");
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
	public void orderTest() throws Exception {
		Team team = new Team("gag");
		String memb1 = "Alb";
		String memb2 = "Fra";
		team.addMember(memb1);
		team.addMember(memb2);
		String[] vet = team.getMembers();
		assertEquals("Alb", vet[0]);
		team.remove(memb1);
		team.remove(memb2);
		
		team.addMember(memb2);
		team.addMember(memb1);
		String[] vet1 = team.getMembers();
		assertEquals("Alb", vet1[0]);
	}

}
