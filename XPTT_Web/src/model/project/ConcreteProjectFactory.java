package model.project;

import java.util.TimeZone;

import model.ConcreteProjectSettings;
import model.ConcreteTeamManager;
import model.Notifier;
import model.ProjectSettings;
import timeline.ConcreteTimeline;
import timeline.Timeline;
import util.serialization.LocalIdentifiabilitySerializer;
import boards.UserStoryBoard.ConcreteUserStoriesManager;
import boards.UserStoryBoard.ProjectUserStoriesManager;
import boards.UserStoryBoard.UserStoriesManager;

/**
 * ConcreteProjectFactory provides implemetation for each method specified in
 * {@link ProjectFactory}, building an integrated system able to write in the
 * project's {@link Timeline} instance an event for each occuring change. This
 * is performed through DP Mediator oriented architecture: too apply this
 * structure to other components, in order to perform such integration, just get
 * the mediator's instace by calling
 * {@link ConcreteProjectFactory#createNotifier()}
 * 
 * @author simone, lele, incre, andre
 *
 */
public class ConcreteProjectFactory implements ProjectFactory {

	private TimeZone timezone;
	private Timeline timeline = new ConcreteTimeline(timezone,
			new LocalIdentifiabilitySerializer());
	private ProjectSettings settings = new ConcreteProjectSettings();
	private Notifier projectManager = new ConcreteTeamManager(settings,
			timeline);

	/**
	 * Creates a new instance of this class
	 * 
	 * @param timezone
	 *            : the {@link TimeZone} to be used for time references
	 */
	public ConcreteProjectFactory(TimeZone timezone) {
		super();
		this.timezone = timezone;
	}

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
	public Notifier createNotifier() {
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
