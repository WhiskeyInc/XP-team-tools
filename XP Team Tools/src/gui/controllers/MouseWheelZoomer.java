package gui.controllers;

import gui.drawables.Zoomable;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelZoomer extends Zoomer implements MouseWheelListener {

	public MouseWheelZoomer(Zoomable... zoomables) {
		super();
		for (Zoomable zoomable : zoomables) {
			super.addZoomable(zoomable);
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	public void mouseWheelMoved(MouseWheelEvent event) {
		int rotation = event.getWheelRotation();
		if (rotation<0) {
			zoomIn();
		}
		else {
			zoomOut();
		}
	}

}
