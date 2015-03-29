package gui.drawables;

import java.awt.Graphics;

import filtering.NoFilter;
import timeline.Event;
import timeline.Timeline;

public class DrawableTimeline implements Drawable {
	
	private Timeline timeline;

	public DrawableTimeline(Timeline timeline) {
		super();
		this.timeline = timeline;
	}
	
	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {
		for (Event event : timeline.getEvents(new NoFilter<Event>())) {
			DrawableEvent drawableEvent = new DrawableEvent(event);
			drawableEvent.draw(g, x, y, width, height);
			x = x + 2*width;
		}
	}

}
