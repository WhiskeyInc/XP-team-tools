package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeline.Event;
import control.actions.filtering.NoFilterGenerator;
import control.actions.filtering.events.EventPeriodFilterGenerator;
import control.actions.filtering.events.NameEventCheckerGenerator;
import control.actions.filtering.events.ParticipantEventCheckerGenerator;

/**
 * Servlet implementation class TimelineController
 */
@WebServlet("/FilteringController")
public class FilteringController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HashMap<String, HttpAction> actions = new HashMap<String, HttpAction>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilteringController() {
		super();
		this.initializeMap();
	}

	private void initializeMap() {
		this.actions.put("noFilterEvent", new NoFilterGenerator<Event>("timeline.jsp"));
		this.actions.put("nameEventFilter", new NameEventCheckerGenerator());
		this.actions.put("participantEventFilter", new ParticipantEventCheckerGenerator());
		this.actions.put("periodEventFilter", new EventPeriodFilterGenerator());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.actions.get(request.getParameter("action")).perform(request, response);
	}

}
