package model.project;

import model.ProjectManager;
import model.ProjectSettings;
import timeline.Timeline;
import boards.UserStoryBoard.UserStoriesManager;

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
