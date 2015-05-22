package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import control.actions.timeline.JSONActions.AutomaticEventAdderFromJSON;
import control.actions.timeline.JSONActions.EventAdderFromJSON;
import control.actions.timeline.JSONActions.MacroEventsListDealer;

/**
 * This HttpServlet performs any action related to JSON communication between servers
 */
@WebServlet("/JSONAcceptor")
public class JSONAcceptor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, HttpAction> actions = new HashMap<String, HttpAction>();

	public JSONAcceptor() {
		super();
		this.initializeMap();
	}

	private void initializeMap() {
		this.actions.put("addAutomaticEvent", new AutomaticEventAdderFromJSON());
		this.actions.put("addEvent", new EventAdderFromJSON());
		this.actions.put("macroEventsRequest", new MacroEventsListDealer());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.actions.get(this.getAction(request, response)).perform(request, response);
	}

	private String getAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		BufferedReader reader = request.getReader();
		String firstLine = reader.readLine();
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(firstLine);
			this.checkUserName(response, json);
			return (String) json.get("action");
		} catch (ParseException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;	
		}
	}
	
	@SuppressWarnings("unchecked")
	private void checkUserName(HttpServletResponse response, JSONObject json) throws IOException{
		HashMap<String, String> users = (HashMap<String, String>) super.getServletContext().getAttribute("users");
		String toBeChecked = (String) json.get("user");
		if (!users.containsKey(toBeChecked)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}		
	}
}
