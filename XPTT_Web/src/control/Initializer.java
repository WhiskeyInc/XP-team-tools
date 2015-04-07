package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import timeline.ConcreteTimeline;

/**
 * Servlet implementation class Initializer
 */
@WebServlet("/initialize")
public class Initializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		if (super.getServletContext().getAttribute("timeline") == null) {
			super.getServletContext().setAttribute("timeline",
					new ConcreteTimeline());
		}		
	}
}