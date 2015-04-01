package model;

import boards.UserStoryBoard.UserStory;
import boards.taskBoard.Task;

public interface TeamManager {

	public void userStoryAdded(UserStory userStory);

	public void userStoryDeleted(UserStory userStory);

	public void userStoryStateChanged(UserStory userStory, String newState);

	public void userStoryPriorityChanged(UserStory userStory, String newPriority);

	public boolean isValidUserStoryState(String state);
	
	public boolean isValidUserStoryPriority(String priority);

	public boolean isValidTaskState(String state);

	public boolean isValidMember(String member);

	public void developersAdded(Task task, String... developers);

	public void taskStateChanged(Task task, String newState);

	public void taskDeleted(Task task);

	public void taskAdded(Task task);

}