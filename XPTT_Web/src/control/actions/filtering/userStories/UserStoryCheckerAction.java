package control.actions.filtering.userStories;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import boards.UserStoryBoard.UserStory;
import control.actions.filtering.CheckerAction;

/**
 * This class represents an abstraction of a generic action dealing with
 * filtering of UserStory instances. It inherits the methods of
 * {@link CheckerAction} and adds the redirection to a proper resource.
 * 
 * @author lele, simo, incre, andre
 * @see {@link CheckerAction}
 */

public abstract class UserStoryCheckerAction extends CheckerAction<UserStory> {

	/**
	 * Redirect the response to "userstory.jsp"
	 * 
	 * @param response
	 *            : the {@link HttpServletResponse} for the specific request
	 * @throws IOException
	 */
	protected void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("userstory.jsp");
	}

}
