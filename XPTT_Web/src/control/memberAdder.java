package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConcreteTeamSettings;
import model.exceptions.NameAlreadyInUseException;

/**
 * Servlet implementation class {@link memberAdder}
 */
@WebServlet("/memberAdder")
public class memberAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		try {
			settings.addTeamMember(request.getParameter("member"));
		} catch (NameAlreadyInUseException e) {
		}
		response.sendRedirect("timeline.jsp");
	}

}
