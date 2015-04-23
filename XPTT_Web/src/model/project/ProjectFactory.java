package model.project;

import boards.UserStoryBoard.UserStoriesManager;
import model.ProjectManager;
import model.ProjectSettings;
import timeline.Timeline;

/**
 * ProjectFactory interface provides DP Abstract Factory oriented methods to
 * build an instance of {@link Project} class.
 * 
 * @author simone
 *
 */
public interface ProjectFactory {

	public Timeline createTimeline();

	public ProjectManager createManager();

	public UserStoriesManager createUserStoriesManager();

	public ProjectSettings createSettings();
}
