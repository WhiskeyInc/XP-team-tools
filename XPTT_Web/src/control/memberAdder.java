package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConcreteTeamSettings;
import model.Member;
import model.exceptions.NameAlreadyInUseException;

/**
 * Servlet implementation class {@link memberAdder}
 */
@WebServlet("/memberAdder")
public class memberAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PASSWORD = "hardCodedPWD";

	public memberAdder() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ConcreteTeamSettings settings = (ConcreteTeamSettings) request
				.getServletContext().getAttribute("settings");
		String defaultDestination = "members.jsp";
		try {
			if (request.getParameter("password").equals(PASSWORD)) {
				settings.addTeamMember(getMember(request));
				defaultDestination = "timeline.jsp";
			}
			response.sendRedirect(defaultDestination);
		} catch (NameAlreadyInUseException e) {
			response.sendRedirect(defaultDestination);
		}
	}

	private Member getMember(HttpServletRequest request) {
		String name = request.getParameter("name");
		String lastName = request.getParameter("surname");
		String role = request.getParameter("role");
		return new Member(name, lastName, role);
	}

}
