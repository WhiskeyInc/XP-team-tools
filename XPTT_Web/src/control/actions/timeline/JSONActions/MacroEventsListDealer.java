package control.actions.timeline.JSONActions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import timeline.Event;
import timeline.Timeline;
import timeline.events.MacroEvent;
import control.HttpAction;
import filtering.NoFilter;

public class MacroEventsListDealer extends JSONAction implements HttpAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json = super.getJSONFromRequest(request, response);
		Timeline timeline = super.getTimeline(request, json);
		ArrayList<Event> macroEvents = this.getMacroEventsOnly(timeline.getEvents(new NoFilter<Event>()));
		JSONObject jsonResponse = this.writeJSON(macroEvents, request, response);
		this.sendResponse(response, jsonResponse);
	}

	@SuppressWarnings("unchecked")
	private JSONObject writeJSON(ArrayList<Event> events,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		JSONArray names = new JSONArray();
		JSONArray ids = new JSONArray();
		for (Event event : events) {
			names.add(event.toString());
			ids.add(event.getId());
		}
		json.put("user", super.getFieldFromJson(super.getJSONFromRequest(request, response), "user"));
		json.put("action", "macro_event_response");
		json.put("names", names);
		json.put("ids", ids);
		return json;
		
	}

	private void sendResponse(HttpServletResponse response, JSONObject json)
			throws IOException {
		PrintWriter writer = response.getWriter();
		writer.write(json.toJSONString());
	}

	private ArrayList<Event> getMacroEventsOnly(ArrayList<Event> events) {
		ArrayList<Event> macroEvents = new ArrayList<Event>();
		for (Event event : events) {
			if(event instanceof MacroEvent){ // TODO: brutto!!!!!
				macroEvents.add(event);
			}
		}
		return macroEvents;
	}
}
