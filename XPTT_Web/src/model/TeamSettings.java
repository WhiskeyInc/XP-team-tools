package model;

import java.util.ArrayList;

public interface TeamSettings {

	public ArrayList<Member> getTeamMembers();

	public ArrayList<String> getPossibleTaskStates();

	public ArrayList<String> getPossibleUserStoryStates();
	
	public boolean isValidUserStoryPriority(int priority);
	
}