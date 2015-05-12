package control.actions.filtering.userStories;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import boards.UserStoryBoard.UserStory;
import control.actions.filtering.CheckerAction;

public abstract class UserStoryCheckerAction extends CheckerAction<UserStory> {

	protected void redirect(HttpServletResponse response) throws IOException{
		response.sendRedirect("userstory.jsp");
	}

}
