package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NameAlreadyInUseException;
import model.project.Project;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;

public class USAdder implements HttpAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Project project = (Project) request.getSession().getAttribute("currentProject");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		UserStory story = new UserStory(title, description, new ConcreteTaskManager());
		try {
			project.getUserStoriesManager().addUserStory(story);
		} catch (NameAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userstory.jsp");
	}

}
