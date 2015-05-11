package ui.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.TeamListUI;
import client.model.ClientConnectionDetails;
import client.model.StrategyClient1_1;


public class TeamListUIMain {
	public static void main(String[] args) {
		StrategyClient1_1 client = new StrategyClient1_1(new ClientConnectionDetails("ALB", "BO"));
		String[] teams = new String[4];
		teams[0] = "LeFere";
		teams[1] = "Frades";
		teams[2] = "NonSoComeChiamarti";
		teams[3] = "XPProgrammers";
		
		
		final String[] teams1 = new String[5];
		teams1[0] = "LeFere";
		teams1[1] = "Frades";
		teams1[2] = "NonSoComeChiamarti";
		teams1[3] = "XPProgrammers";
		teams1[4] = "JavaProg";
		
		
		final TeamListUI listUI = new TeamListUI(client, teams);
		
		listUI.setCreateListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listUI.removeTeamPanel();
				listUI.fillTeamPane(teams1);
				listUI.refresh();
				//listUI.setLabelListener();
			}
		});
		
		
	}

}
