package gui.drawables;

import java.awt.Graphics;

import timeline.Event;

public class DrawableEvent implements Drawable {

	private Event event;

	public DrawableEvent(Event event) {
		super();
		this.event = event;
	}
	
	/* (non-Javadoc)
	 * @see gui.drawables.Drawable#draw(java.awt.Graphics, int, int, int, int)
	 */
	@Override
	public void draw(Graphics g, int x, int y, int width, int height){
		g.drawRoundRect(x, y, width, height, 30, 30);
		int stringSpace = (int) (0.8 * width);
		int margin = (int) (width - stringSpace)/2;
		g.drawString(drawableString(event.toString(), g, stringSpace), x + margin, y+height/2);
	}

	private String drawableString(String string, Graphics g, int stringSpace) {
		int stringLength = g.getFontMetrics().stringWidth(string + "...");
		boolean modified = false;
		while(stringLength > stringSpace){
			try {
				string = string.substring(0, string.length()-2);
			} catch (IndexOutOfBoundsException e) {
				return "...";
			}
			stringLength = g.getFontMetrics().stringWidth(string + "...");
			modified = true;
		}
		if (modified) {
			return string + "...";
		}
		return string;
		
	}

	@Override
	public void zoomIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zoomOut() {
		// TODO Auto-generated method stub
		
	}

	
}
