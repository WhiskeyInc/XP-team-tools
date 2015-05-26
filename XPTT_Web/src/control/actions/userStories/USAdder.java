package control.actions.userStories;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NameAlreadyInUseException;
import model.project.Project;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import control.HttpAction;

/**
 * This class adds a user story to the board of the project the user is
 * visualizing. To work properly, this class requires that the following
 * attributes are properly set in the request: title, description.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}, {@link UserStory}
 *
 */
public class USAdder implements HttpAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Project project = (Project) request.getSession().getAttribute(
				"currentProject");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		UserStory story = new UserStory(title, description,
				new ConcreteTaskManager());
		try {
			project.getUserStoriesManager().addUserStory(story);
		} catch (NameAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userstory.jsp");
	}

}
