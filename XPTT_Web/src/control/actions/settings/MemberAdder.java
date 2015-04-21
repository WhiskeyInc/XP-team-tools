package control.actions.settings;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConcreteTeamSettings;
import model.Member;

public class MemberAdder extends SettingsAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			((ConcreteTeamSettings) super.getSettings(request)).addTeamMember(this.getMember(request));
		} catch (Exception e) {
			request.getSession().setAttribute("exception", e);
		}
		response.sendRedirect("members.jsp");
	}
	
	private Member getMember(HttpServletRequest request) throws Exception{
		String name = request.getParameter("name");
		String lastName = request.getParameter("surname");
		String role = request.getParameter("role");
		if ((name.trim().equals("")||lastName.trim().equals("")||role.trim().equals(""))) {
			throw new Exception("Invalid input");
		}
		return new Member(name, lastName, role);
	}

}
