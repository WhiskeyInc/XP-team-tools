package control.actions.settings;

import javax.servlet.http.HttpServletRequest;

import model.TeamSettings;
import control.actions.AdministrationAction;

public abstract class SettingsAction extends AdministrationAction {
	
	protected TeamSettings getSettings(HttpServletRequest request) throws AccessDeniedException{
		if (super.autenticateUser(request)) {
			return (TeamSettings) request.getServletContext().getAttribute("settings");
		}
		throw new AccessDeniedException();
	}

}
