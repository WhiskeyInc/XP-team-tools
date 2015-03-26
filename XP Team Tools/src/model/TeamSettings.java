package model;

import java.util.ArrayList;

import timeline.Event;

public class TeamSettings {
	
	private ArrayList<String> teamMembers = new ArrayList<String>();
	private ArrayList<String> possibleStates = new ArrayList<String>();
	
	public void setPossibleStates(String... possibleStates) {
		for (String state : possibleStates) {
			this.possibleStates.add(state);
		}		
	}

	public ArrayList<String> getTeamMembers() {
		return teamMembers;
	}

	public ArrayList<String> getPossibleStates() {
		return possibleStates;
	}

	public void addTeamMember(String member) {
		this.teamMembers.add(member);
	}

	
}