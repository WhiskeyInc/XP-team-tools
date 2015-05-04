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

import control.actions.timeline.JSONActions.EventAdderFromJSON;

/**
 * Servlet implementation class EventAdderFormChat
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
		this.actions.put("addEvent", new EventAdderFromJSON());
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
			System.err.println((String) json.get("action"));
			return (String) json.get("action");
		} catch (ParseException e) {
			// TODO Auto-generated catch block //Gestire con codice di
			// fallimento???
			e.printStackTrace();
			return null;
		}
		
	}
}
