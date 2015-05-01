package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.actions.projects.ProjectAdder;
import control.actions.projects.ProjectSelector;

/**
 * Servlet implementation class ProjectsController
 */
@WebServlet("/ProjectsController")
public class ProjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, HttpAction> actions = new HashMap<String, HttpAction>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectsController() {
		super();
		this.initializeMap();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.actions.get(request.getParameter("action")).perform(request,
				response);
	}

	private void initializeMap() {
		this.actions.put("addition", new ProjectAdder());
		this.actions.put("selection", new ProjectSelector());
	}
}
