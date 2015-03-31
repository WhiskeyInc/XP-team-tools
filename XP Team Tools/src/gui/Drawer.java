package gui;

import gui.drawables.Drawable;
import gui.drawables.Zoomable;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Drawer extends JPanel implements Zoomable {

	private static final long serialVersionUID = 1L;
	private Drawable[] drawables;

	public Drawer(Drawable... drawables) {
		this.drawables = drawables;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draw(g);
	}

	public void draw(Graphics g) {
		int x = super.getX();
		int y = super.getY();
		int width = super.getWidth();
		int height = super.getHeight();
		for (Drawable drawable : drawables) {
			drawable.draw(g, x, y, width, height);
		}
	}

	@Override
	public void zoomIn() {
		for (Drawable drawable : drawables) {
			drawable.zoomIn();
		}
		super.repaint();
	}

	@Override
	public void zoomOut() {
		for (Drawable drawable : drawables) {
			drawable.zoomOut();
		}
		super.repaint();
	}
}
