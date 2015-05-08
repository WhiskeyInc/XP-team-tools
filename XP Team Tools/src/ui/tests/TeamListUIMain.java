package ui.tests;

import client.model.ClientConnectionDetails;
import client.model.StrategyClient1_1;
import ui.TeamListUI;


public class TeamListUIMain {
	public static void main(String[] args) {
		StrategyClient1_1 client = new StrategyClient1_1(new ClientConnectionDetails("ALB", "BO"));
		String[] teams = new String[4];
		teams[0] = "LeFere";
		teams[1] = "Frades";
		teams[2] = "NonSoComeChiamarti";
		teams[3] = "XPProgrammers";
		
		TeamListUI listUI = new TeamListUI(client, teams);
		
	}

}
