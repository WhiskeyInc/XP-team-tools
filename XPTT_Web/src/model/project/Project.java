package model.project;

import model.ProjectManager;
import model.ProjectSettings;
import timeline.Timeline;
import util.serialization.Serializable;
import boards.UserStoryBoard.UserStoriesManager;

public class Project extends Serializable{

	private Timeline timeline;
	private UserStoriesManager userStoriesmanager;
	private ProjectManager projectManager;
	private ProjectSettings settings;
	private String name;

	public Project(String name, ProjectFactory factory) {
		super();
		this.name = name;
		this.timeline = factory.createTimeline();
		this.projectManager = factory.createManager();
		this.userStoriesmanager = factory.createUserStoriesManager();
		this.settings = factory.createSettings();
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

	public ProjectSettings getSettings() {
		return this.settings;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
