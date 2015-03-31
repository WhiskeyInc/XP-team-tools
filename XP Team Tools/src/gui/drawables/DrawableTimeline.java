package gui.drawables;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;

import model.exceptions.NoSuchEventException;
import timeline.Event;
import timeline.Timeline;
import filtering.NoFilter;

public class DrawableTimeline implements Drawable {

	private static final int EVENTBOX_WIDTH = 120;
	private static final int EVENTBOX_HEIGHT = 60;
	private static final int ROW_Y_MIN_MARGIN = 30;
	private static final int ROW_Y_MAX_MARGIN = 490;
	private static final int MARGIN = 10 + EVENTBOX_WIDTH / 2;
	private static final int SECONDS_PER_PIXEL = 605; // A week is approximately
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
		int initialX = x + MARGIN;
		int rowY = y + (int) (ROWY_PERCENTAGE * height);
		g.drawLine(initialX, rowY, initialX + timelineSeconds
				/ SECONDS_PER_PIXEL, rowY);

		for (Event event : timeline.getEvents(new NoFilter<Event>())) {
			DrawableEvent drawableEvent = new DrawableEvent(event);
			int lineY = getY(rowY);
			drawableEvent.draw(g, getEventX(initialX, event), lineY - EVENTBOX_HEIGHT,
					EVENTBOX_WIDTH, EVENTBOX_HEIGHT);
			g.drawLine(getLineX(initialX, event), lineY,
					getLineX(initialX, event), rowY);

		}
	}

	private int getY(int rowY) {
		Random randomGenerator = new Random();
		return rowY - ROW_Y_MIN_MARGIN
				- randomGenerator.nextInt(ROW_Y_MAX_MARGIN - ROW_Y_MIN_MARGIN);
	}

	private int getLineX(int initialX, Event event) {
		try {
			return initialX + timelineSeconds(event, timeline.getEvent("creation"))
					/ SECONDS_PER_PIXEL;
		} catch (NoSuchEventException e) {
			return initialX;
		}
	}

	private int getEventX(int initialX, Event event) {
		return getLineX(initialX, event) - EVENTBOX_WIDTH / 2;
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

}