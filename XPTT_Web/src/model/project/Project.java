package model.project;

import model.ProjectManager;
import timeline.Timeline;
import util.serialization.Serializable;
import boards.UserStoryBoard.UserStoriesManager;

public class Project extends Serializable{

	private Timeline timeline;
	private UserStoriesManager userStoriesmanager;
	private ProjectManager projectManager;

	public Project(ProjectFactory factory) {
		super();
		this.timeline = factory.createTimeline();
		this.projectManager = factory.createManager();
		this.userStoriesmanager = factory.createUserStoriesManager();
	}

	public Timeline getTimeline() {
		return this.timeline;
	}

	public UserStoriesManager getUserStoriesManager() {
		return this.userStoriesmanager;
	}

	public ProjectManager getManager() {
		return this.projectManager;
	}
}
