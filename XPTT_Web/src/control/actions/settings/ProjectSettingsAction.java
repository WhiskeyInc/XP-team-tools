package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.ProjectSettings;
import model.project.Project;
import control.HttpAction;

/**
 * This class provides an abstraction for every action which involves the
 * settings of a {@link Project}. It provides the basic methods to get the
 * {@link ProjectSettings} for the current project.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}, {@link ProjectSettings}
 *
 */
public abstract class ProjectSettingsAction implements HttpAction {

	/**
	 * Return the {@link ProjectSettings} of the {@link Project} stored in the
	 * "currentProject" attribute of the session context). If there is not a
	 * project in this attribute, the one stored in the "defaultProject"
	 * attribute of the application context is returned
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return the correct instance of {@link ProjectSettings}
	 */
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
