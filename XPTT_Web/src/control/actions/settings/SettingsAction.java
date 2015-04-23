package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.ProjectSettings;
import control.actions.AdministrationAction;

public abstract class SettingsAction extends AdministrationAction {
	
	protected ProjectSettings getSettings(HttpServletRequest request) throws AccessDeniedException{
		if (super.autenticateUser(request)) {
			return (ProjectSettings) request.getServletContext().getAttribute("settings");
		}
		throw new AccessDeniedException();
	}

}
