package model.project;

import model.Notifier;
import model.ProjectSettings;
import timeline.Event;
import timeline.Timeline;
import util.serialization.Serializable;
import boards.UserStoryBoard.UserStoriesManager;

/**
 * Project class represents a generic software development project. To ensure
 * identifiability, it inherits {@link Serializable} methods.
 * 
 * @author simone, lele, incre, andre
 * @see Serializable
 *
 */
public class Project extends Serializable {

	private Timeline timeline;
	private UserStoriesManager userStoriesmanager;
	private Notifier projectManager;
	private ProjectSettings settings;
	private String name;
	private String description;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param name
	 *            : the name of the created project
	 * @param factory
	 *            : a concrete instance of {@link ProjectFactory} interface,
	 *            trough which each required component will be created
	 * @param description
	 *            : a short description
	 */
	public Project(String name, ProjectFactory factory, String description) {
		super();
		this.name = name;
		this.timeline = factory.createTimeline();
		this.projectManager = factory.createNotifier();
		this.userStoriesmanager = factory.createUserStoriesManager();
		this.settings = factory.createSettings();
		this.description = description;
	}

	/**
	 * Returns an instance of {@link Timeline} interface, wich represents each
	 * {@link Event} that occurs in this project
	 * 
	 * @return: the timeline of this project
	 */
	public Timeline getTimeline() {
		return this.timeline;
	}

	/**
	 * Returns the instance of {@link UserStoriesManager} that is currently
	 * managing the user stories associated to this project
	 * 
	 * @return: a user stories collector, associated to this project
	 */
	public UserStoriesManager getUserStoriesManager() {
		return this.userStoriesmanager;
	}

	/**
	 * Returns the instance of {@link Notifier} responsible of notifying
	 * externally or internally every time something changes in this project
	 * 
	 * @return: the project notifier
	 */
	public Notifier getManager() {
		return this.projectManager;
	}

	/**
	 * Returns the settings that control data consistance and validity
	 * 
	 * @return: the instance of {@link ProjectSettings} of this project
	 */
	public ProjectSettings getSettings() {
		return this.settings;
	}

	/**
	 * Returns a short description for this project
	 * 
	 * @return: a string representing project's further details
	 */
	public String getDescription() {
		return description;
	}

	@Override
	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name;
	}
}
