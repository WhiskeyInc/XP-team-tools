package control.actions;

import javax.servlet.http.HttpServletRequest;

public abstract class AdministrationAction implements HttpAction {

	private static final String PASSWORD = "hardCodedPWD";

	protected boolean autenticateUser(HttpServletRequest request){
		return request.getParameter("password").equals(PASSWORD);
	}
}
