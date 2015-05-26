package control.actions.timeline.JSONActions;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidDateException;

import org.json.simple.JSONObject;

import timeline.Event;
import timeline.Timeline;

/**
 * This class parses the JSONObject taken from an HttpServletRequest and uses
 * the data to build an event and put it in the timeline of the default project
 * of the user. All the filed defined in this class are required to properly
 * build the event.
 * 
 * @author lele, simo, incre, andre
 * @see {@link JSONAction}
 *
 */
public class EventAdderFromJSON extends JSONAction {

	public static final String EVENT_NAME_FIELD = "event_name";
	public static final String EVENT_YEAR_FIELD = "year";
	public static final String EVENT_MONTH_FIELD = "month";
	public static final String EVENT_DAY_FIELD = "day";
	public static final String EVENT_HOUR_FIELD = "hour";
	public static final String EVENT_MIN_FIELD = "min";

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
		String name = super.getFieldFromJson(json, EventAdderFromJSON.EVENT_NAME_FIELD);
		Event event = new Event(name, this.getDateFromJSON(json));
		super.addParticipantsToEvent(json, event);
		Timeline timeline = super.getTimeline(request, json);
		try {
			timeline.addEvent(event);
		} catch (InvalidDateException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private GregorianCalendar getDateFromJSON(JSONObject json) {
		int year = Integer.parseInt((String) this
				.getFieldFromJson(json, EventAdderFromJSON.EVENT_YEAR_FIELD));
		int month = Integer.parseInt((String) this.getFieldFromJson(json,
				EventAdderFromJSON.EVENT_MONTH_FIELD));
		int day = Integer.parseInt((String) this.getFieldFromJson(json, EventAdderFromJSON.EVENT_DAY_FIELD));
		int hour = Integer.parseInt((String) this
				.getFieldFromJson(json, EventAdderFromJSON.EVENT_HOUR_FIELD));
		int min = Integer.parseInt((String) this.getFieldFromJson(json, EventAdderFromJSON.EVENT_MIN_FIELD));
		GregorianCalendar date = new GregorianCalendar(year, month, day, hour,
				min);
		return date;
	}

}