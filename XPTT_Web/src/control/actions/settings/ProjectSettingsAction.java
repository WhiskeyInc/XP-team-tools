package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.ProjectSettings;
import model.project.Project;
import control.HttpAction;
import control.services.LoginService;

public abstract class ProjectSettingsAction implements HttpAction {

	protected ProjectSettings getSettings(HttpServletRequest request)
			throws AccessDeniedException {
		if (LoginService .autenticateUser(request)) {
			Project project = (Project) request.getSession().getAttribute(
					"currentProject");
			if (project == null) {
				project = (Project) request.getServletContext().getAttribute(
						"defaultProject");
			}
			return project.getSettings();
		}
		throw new AccessDeniedException();
	}

}
