package control.actions.settings;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConcreteTeamSettings;
import model.Member;
import model.exceptions.NameAlreadyInUseException;

public class MemberAdder extends SettingsAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			((ConcreteTeamSettings) super.getSettings(request)).addTeamMember(this.getMember(request));
		} catch (NameAlreadyInUseException e) {
			//TODO
		} catch (AccessDeniedException e) {
			//TODO
		}
		response.sendRedirect("members.jsp");
	}
	
	private Member getMember(HttpServletRequest request) {
		String name = request.getParameter("name");
		String lastName = request.getParameter("surname");
		String role = request.getParameter("role");
		return new Member(name, lastName, role);
	}

}
