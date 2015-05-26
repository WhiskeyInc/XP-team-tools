package control.actions.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class implements the log out service by removing the attributes
 * referring to the current user and the project in use from the session
 * context.
 * 
 * @author lele, simo, incre, andre
 * @see {@link AccountAction}
 *
 */
public class SignOutAction extends AccountAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("currentUser");
		request.getSession().removeAttribute("currentProject");
		super.forward(response);
	}

}
