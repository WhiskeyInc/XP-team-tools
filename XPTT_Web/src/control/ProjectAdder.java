package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;

/**
 * Servlet implementation class ProjectAdder
 */
@WebServlet("/ProjectAdder")
public class ProjectAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProjectsCollector projects = (ProjectsCollector) request
				.getServletContext().getAttribute("projects");
		projects.addProject(new Project(request.getParameter("name"),
				new ConcreteProjectFactory()));
	}

}
