package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpAction represents any kind of action that can be performed in a HTTP
 * context
 * 
 * @author lele, simone, incre, andre
 *
 */
public interface HttpAction {

	/**
	 * Performs the action
	 * 
	 * @param request
	 *            : the related HttpServletRequest object
	 * @param response
	 *            : the related HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
