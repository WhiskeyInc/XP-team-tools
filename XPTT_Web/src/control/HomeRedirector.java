package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This HttpServlet provides response proper redirecting according to the login
 * situation
 */
@WebServlet("/home")
public class HomeRedirector extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String destination = getDestinationURI(request);
		request.getRequestDispatcher(destination).forward(request, response);
	}

	private String getDestinationURI(HttpServletRequest request) {
		String destination;
		if (request.getSession().getAttribute("currentUser") == null) {
			destination = "home.jsp";
		} else {
			destination = "projects.jsp";
		}
		return destination;
	}
}
