package model.project;

import model.Notifier;
import model.ProjectSettings;
import timeline.Event;
import timeline.Timeline;
import boards.UserStoryBoard.UserStoriesManager;

/**
 * ProjectFactory interface provides DP Abstract Factory oriented methods to
 * build an instance of {@link Project} class.
 * 
 * @author simone, lele, incre, andre
 *
 */
public interface ProjectFactory {

	/**
	 * Returns the {@link Timeline} object
	 * 
	 * @return: the object to be used for {@link Event} collection
	 */
	public Timeline createTimeline();

	/**
	 * Returns the {@link Notifier} object
	 * 
	 * @return: the project's notifier
	 */
	public Notifier createNotifier();

	/**
	 * Returns the {@link UserStoriesManager} object
	 * 
	 * @return: the manager of the project's user stories
	 */
	public UserStoriesManager createUserStoriesManager();

	/**
	 * Returns the {@link ProjectSettings} object
	 * 
	 * @return: the project's settings
	 */
	public ProjectSettings createSettings();
}
