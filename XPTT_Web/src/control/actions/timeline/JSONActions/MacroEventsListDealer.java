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

/**
 * This class builds a list with all the {@link MacroEvent} in the
 * {@link Timeline} of the default project of a specific user and puts it in a
 * {@link JSONObject}. It is then put in the {@link HttpServletResponse} and
 * sent as a response to a proper request. 
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}, {@link JSONAction}
 *
 */
public class MacroEventsListDealer extends JSONAction {

	public static final String ACTION_FIELD = "action";
	public static final String NAMES_FIELD = "names";
	public static final String IDS_FIELD = "ids";
	public static final String MACRO_EVENT_LIST_RESPONSE = "macro_event_response";

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * control.actions.timeline.JSONActions.JSONAction#perform(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json = super.getJSONFromRequest(request, response);
		Timeline timeline = super.getTimeline(request, json);
		ArrayList<Event> macroEvents = this.getMacroEventsOnly(timeline
				.getEvents(new NoFilter<Event>()));
		JSONObject jsonResponse = this
				.writeJSON(macroEvents, request, response);
		this.sendResponse(response, jsonResponse);
	}

	@SuppressWarnings("unchecked")
	private JSONObject writeJSON(ArrayList<Event> events,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JSONObject json = new JSONObject();
		JSONArray names = new JSONArray();
		JSONArray ids = new JSONArray();
		for (Event event : events) {
			names.add(event.toString());
			ids.add(event.getId());
		}
		json.put(JSONAction.USER_FIELD, super.getFieldFromJson(
				super.getJSONFromRequest(request, response),
				JSONAction.USER_FIELD));
		json.put(MacroEventsListDealer.ACTION_FIELD,
				MacroEventsListDealer.MACRO_EVENT_LIST_RESPONSE);
		json.put(MacroEventsListDealer.NAMES_FIELD, names);
		json.put(MacroEventsListDealer.IDS_FIELD, ids);
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
			if (event instanceof MacroEvent) {
				macroEvents.add(event);
			}
		}
		return macroEvents;
	}
}
