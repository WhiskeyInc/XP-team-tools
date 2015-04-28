package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.ProjectSettings;
import model.project.Project;
import control.actions.AdministrationAction;

public abstract class SettingsAction extends AdministrationAction {

	protected ProjectSettings getSettings(HttpServletRequest request)
			throws AccessDeniedException {
		if (super.autenticateUser(request)) {
			Project project = (Project) request.getServletContext()
					.getAttribute("currentProject");
			return project.getSettings();
		}
		throw new AccessDeniedException();
	}

}