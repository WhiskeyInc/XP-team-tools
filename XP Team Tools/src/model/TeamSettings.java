package model;

import java.util.ArrayList;

public interface TeamSettings {

	public ArrayList<String> getTeamMembers();

	public ArrayList<String> getPossibleTaskStates();

	public ArrayList<String> getPossibleUserStoryStates();
	
	public ArrayList<String> getPossibleUserStoryPriorities();

}