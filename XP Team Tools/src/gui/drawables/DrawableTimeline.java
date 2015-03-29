package gui.drawables;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import timeline.Event;
import timeline.Timeline;
import filtering.NoFilter;

public class DrawableTimeline implements Drawable {
	
	private static final int MARGIN = 10;
	private static final int SECONDS_PER_PIXEL = 605; //A week is approximately 1000 px
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
		int rowY = y + (int) (ROWY_PERCENTAGE*height);
		g.drawLine(initialX, rowY, initialX + timelineSeconds/SECONDS_PER_PIXEL, rowY);
		
		
		

//		for (Event event : timeline.getEvents(new NoFilter<Event>())) {
//			DrawableEvent drawableEvent = new DrawableEvent(event);
//			drawableEvent.draw(g, x, y, width, height);
//			x = x + 2*width;
//		}
	}

	private int getTimelineSeconds() {
		Event lastEvent = getLastEvent();
		Event initialEvent = timeline.getEvent("creation");
		int timelineSeconds = timelineSeconds(lastEvent, initialEvent);
		return timelineSeconds;
	}

	private int timelineSeconds(Event lastEvent, Event initialEvent) {
		GregorianCalendar lastDate = lastEvent.getDate();
		GregorianCalendar initialDate = initialEvent.getDate();
		int secondsOfFirstEvent = (int) (initialDate.getTimeInMillis()/1000);
		int secondsToLastEvent = (int) (lastDate.getTimeInMillis()/1000);
		int timelineSeconds = secondsToLastEvent - secondsOfFirstEvent;
		return timelineSeconds;
	}

	private Event getLastEvent() {
		ArrayList<Event> list = timeline.getEvents(new NoFilter<Event>());
		Collections.sort(list);
		Event lastEvent = list.get(list.size()-1);
		return lastEvent;
	}

}