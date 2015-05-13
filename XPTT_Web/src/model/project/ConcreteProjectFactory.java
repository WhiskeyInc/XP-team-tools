package model.project;

import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteTeamManager;
import model.ProjectManager;
import model.ProjectSettings;
import timeline.ConcreteTimeline;
import timeline.Timeline;
import util.serialization.LocalUniquenessSerializer;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.ProjectUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;

public class ConcreteProjectFactory implements ProjectFactory {

	private Timeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"), new LocalUniquenessSerializer() );
	private ProjectSettings settings = new ConcreteProjectSettings();
	private ProjectManager projectManager = new ConcreteTeamManager(settings,
			timeline);

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.project.ProjectFactory#createTimeline()
	 */
	public Timeline createTimeline() {
		return timeline;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.project.ProjectFactory#createManager()
	 */
	public ProjectManager createManager() {
		return projectManager;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.project.ProjectFactory#createUserStoriesManager()
	 */
	public UserStoriesManager createUserStoriesManager() {
		return new ProjectUserStoriesManager(new ConcreteUserStoriesManager(),
				projectManager);
	}

	@Override
	public ProjectSettings createSettings() {
		return this.settings;
	}
}
