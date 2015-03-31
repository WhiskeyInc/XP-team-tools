package gui.controllers;

import gui.drawables.Zoomable;

import java.util.ArrayList;

public class Zoomer {

	private ArrayList<Zoomable> zoomables = new ArrayList<Zoomable>();

	public void addZoomable(Zoomable zoomableItem) {
		this.zoomables.add(zoomableItem);
	}

	protected void zoomIn() {
		for (Zoomable zoomable : zoomables) {
			zoomable.zoomIn();
		}
	}

	protected void zoomOut() {
		for (Zoomable zoomable : zoomables) {
			zoomable.zoomOut();
		}
	}
}
