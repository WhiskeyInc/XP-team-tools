package control.actions.settings;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConcreteProjectSettings;
import model.TeamComponent;

public class MemberAdder extends ProjectSettingsAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			((ConcreteProjectSettings) super.getSettings(request)).addTeamMember(this.getMember(request));
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		response.sendRedirect("members.jsp");
	}
	
	private TeamComponent getMember(HttpServletRequest request) throws Exception{
		String name = request.getParameter("name");
		String lastName = request.getParameter("surname");
		String role = request.getParameter("role");
		if ((name.trim().equals("")||lastName.trim().equals("")||role.trim().equals(""))) {
			throw new Exception("Invalid input");
		}
		return new TeamComponent(name, lastName, role);
	}

}
