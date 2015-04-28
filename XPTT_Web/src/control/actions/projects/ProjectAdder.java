package control.actions.projects;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;

public class ProjectAdder extends ProjectAction {

	@Override
	/*
	 * (non-Javadoc)
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectsCollector projects = super.getProjects(request);
		projects.addProject(new Project(request.getParameter("name"),
				new ConcreteProjectFactory()));
		super.redirect(response);
	}

}
