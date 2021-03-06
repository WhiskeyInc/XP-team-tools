package gui;

import gui.controllers.MouseWheelZoomer;
import gui.drawables.DrawableTimeline;

import java.util.GregorianCalendar;

import javax.swing.JFrame;

import model.exceptions.InvalidDateException;
import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;

public class GUITest03 {

	public static void main(String[] args) throws InvalidDateException {
		JFrame frame = new JFrame("GUI Test02");
		Timeline timeline = new ConcreteTimeline();
		timeline.addEvent(new Event("Riunione", new GregorianCalendar(2015, 02,
				31, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione1", new GregorianCalendar(2015,
				03, 12, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione2", new GregorianCalendar(2015,
				03, 10, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione3", new GregorianCalendar(2015,
				03, 6, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione4", new GregorianCalendar(2015,
				03, 3, 23, 44, 04)));
		DrawableTimeline drawableTimeline = new DrawableTimeline(timeline);
		Drawer drawer = new Drawer(drawableTimeline);
		drawer.addMouseWheelListener(new MouseWheelZoomer(drawer));
		frame.getContentPane().add(drawer);
		frame.pack();
		frame.setSize(800, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
