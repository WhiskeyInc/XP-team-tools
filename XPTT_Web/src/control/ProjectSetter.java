package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;

/**
 * Servlet implementation class ProjectSetter
 */
@WebServlet("/ProjectSetter")
public class ProjectSetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProjectsCollector collector = (ProjectsCollector) request.getServletContext().getAttribute("projects");
		Project project = collector.getProject(id);
		request.getSession().setAttribute("currentProject", project);
		response.sendRedirect("projects.jsp");
	}

}
