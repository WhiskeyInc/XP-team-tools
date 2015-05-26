package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.ProjectSettings;
import model.project.Project;
import control.HttpAction;

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
