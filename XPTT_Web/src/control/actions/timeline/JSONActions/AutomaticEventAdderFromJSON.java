package control.actions.timeline.JSONActions;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;

import org.json.simple.JSONObject;

import timeline.Event;
import timeline.Timeline;
import timeline.events.AutomaticEvent;
import timeline.events.MacroEvent;

/**
 * This class parses the JSONObject taken from an HttpServletRequest and uses
 * the data to build an {@link AutomaticEvent} and put it in a specified
 * {@link MacroEvent} of the timeline of default project of the user. All the
 * filed defined in this class are required to properly build the event.
 * 
 * @author lele, simo, incre, andre
 * @see {@link JSONAction}
 *
 */

public class AutomaticEventAdderFromJSON extends JSONAction {

	public static final String EVENT_NAME_FIELD = "event_name";
	public static final String EVENT_ID_FIELD = "year";

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
		int id = Integer.parseInt(super.getFieldFromJson(json,
				AutomaticEventAdderFromJSON.EVENT_ID_FIELD));
		Timeline timeline = super.getTimeline(request, json);
		Event event = createAutomaticEventFromJSON(json);
		try {
			MacroEvent macroEvent = (MacroEvent) timeline.getEvent(id);
			macroEvent.addEvent(event);
		} catch (InvalidDateException | NoSuchEventException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private Event createAutomaticEventFromJSON(JSONObject json) {
		String name = super.getFieldFromJson(json,
				AutomaticEventAdderFromJSON.EVENT_NAME_FIELD);
		Event event = new AutomaticEvent(name, TimeZone.getDefault());
		super.addParticipantsToEvent(json, event);
		return event;
	}

}
