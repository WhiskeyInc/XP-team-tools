package model.project;

import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteProjectManager;
import model.ProjectManager;
import model.ProjectSettings;
import timeline.ConcreteTimeline;
import timeline.Timeline;
import util.serialization.LocalIdentifiabilitySerializer;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.ProjectUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;

/**
 * ConcreteProjectFactory provides implementation for each method specified in
 * {@link ProjectFactory}, building an integrated system able to write in the
 * project's {@link Timeline} instance an event for each occurring change. This
 * is performed through DP Mediator oriented architecture: too apply this
 * structure to other components, in order to perform such integration, just get
 * the mediator's instance by calling
 * {@link ConcreteProjectFactory#createNotifier()}
 * 
 * @author simone, lele, incre, andre
 *
 */
public class ConcreteProjectFactory implements ProjectFactory {

	private Timeline timeline = new ConcreteTimeline(TimeZone.getTimeZone("Europe/Rome"),
			new LocalIdentifiabilitySerializer());
	private ProjectSettings settings = new ConcreteProjectSettings();
	private ProjectManager projectManager = new ConcreteProjectManager(settings,
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
	public ProjectManager createNotifier() {
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.project.ProjectFactory#createSettings()
	 */
	public ProjectSettings createSettings() {
		return this.settings;
	}
}