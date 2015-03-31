package gui.drawables;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import model.exceptions.NoSuchEventException;
import timeline.Event;
import timeline.Timeline;
import filtering.NoFilter;

public class DrawableTimeline implements Drawable, Zoomable {

	private static final double TIME_SCALE_FACTOR = 1.6;
	private static final double EVENTS_WIDTH_SCALE_FACTOR = 1.1;
	private int eventBoxWidth = 120;
	private static final int EVENTBOX_HEIGHT = 60;
	private int rowYMargin = 30;
	private int margin = 10 + eventBoxWidth / 2;
	private int secondsPerPixel = 605; // A week is approximately
										// 1000 px
	private static final float ROWY_PERCENTAGE = (float) 0.8;
	private Timeline timeline;

	public DrawableTimeline(Timeline timeline) {
		super();
		this.timeline = timeline;
	}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {

		int timelineSeconds = getTimelineSeconds();
		int initialX = x + margin;
		int rowY = y + (int) (ROWY_PERCENTAGE * height);
		g.drawLine(initialX, rowY,
				initialX + timelineSeconds / secondsPerPixel, rowY);

		for (Event event : timeline.getEvents(new NoFilter<Event>())) {
			DrawableEvent drawableEvent = new DrawableEvent(event);
			int lineY = getY(rowY);
			drawableEvent.draw(g, getEventX(initialX, event), lineY
					- EVENTBOX_HEIGHT, eventBoxWidth, EVENTBOX_HEIGHT);
			g.drawLine(getLineX(initialX, event), lineY,
					getLineX(initialX, event), rowY);

		}
	}

	private int getY(int rowY) {
		return rowY - rowYMargin;
	}

	private int getLineX(int initialX, Event event) {
		try {
			return initialX
					+ timelineSeconds(event, timeline.getEvent("creation"))
					/ secondsPerPixel;
		} catch (NoSuchEventException e) {
			return initialX;
		}
	}

	private int getEventX(int initialX, Event event) {
		return getLineX(initialX, event) - eventBoxWidth / 2;
	}

	private int getTimelineSeconds() {
		Event lastEvent = getLastEvent();
		Event initialEvent;
		try {
			initialEvent = timeline.getEvent("creation");
			int timelineSeconds = timelineSeconds(lastEvent, initialEvent);
			return timelineSeconds;
		} catch (NoSuchEventException e) {
			return 0;
		}
	}

	private int timelineSeconds(Event lastEvent, Event initialEvent) {
		GregorianCalendar lastDate = lastEvent.getDate();
		GregorianCalendar initialDate = initialEvent.getDate();
		int secondsOfFirstEvent = (int) (initialDate.getTimeInMillis() / 1000);
		int secondsToLastEvent = (int) (lastDate.getTimeInMillis() / 1000);
		int timelineSeconds = secondsToLastEvent - secondsOfFirstEvent;
		return timelineSeconds;
	}

	private Event getLastEvent() {
		ArrayList<Event> list = timeline.getEvents(new NoFilter<Event>());
		Collections.sort(list);
		Event lastEvent = list.get(list.size() - 1);
		return lastEvent;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.drawables.Zoomable#zoomIn()
	 */
	public void zoomIn() {
		eventBoxWidth = (int) (eventBoxWidth * EVENTS_WIDTH_SCALE_FACTOR);
		secondsPerPixel = (int) (secondsPerPixel / TIME_SCALE_FACTOR);
		margin = 10 + eventBoxWidth / 2;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.drawables.Zoomable#zoomOut()
	 */
	public void zoomOut() {
		eventBoxWidth = (int) (eventBoxWidth / EVENTS_WIDTH_SCALE_FACTOR);
		secondsPerPixel = (int) (secondsPerPixel * 1.6);
		margin = 10 + eventBoxWidth / 2;
	}

}