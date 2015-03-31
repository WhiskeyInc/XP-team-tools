package gui.drawables;

import java.awt.Graphics;

public interface Drawable extends Zoomable{

	public void draw(Graphics g, int x, int y, int width, int height);
}