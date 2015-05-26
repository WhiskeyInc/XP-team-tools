package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.ProjectSettings;
import model.project.Project;
import control.HttpAction;

/**
 * This class provides an abstraction for every action which involves the settings
 * of a {@link Project}. It provides the basic methods to get the
 * {@link ProjectSettings} for the current project.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}, {@link ProjectSettings}
 *
 */
public abstract class ProjectSettingsAction implements HttpAction {

	protected ProjectSettings getSettings(HttpServletRequest request) {
		Project project = (Project) request.getSession().getAttribute(
				"currentProject");
		if (project == null) {
			project = (Project) request.getServletContext().getAttribute(
					"defaultProject");
		}
		return project.getSettings();

	}

}
