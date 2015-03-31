package gui;

import gui.drawables.DrawableTimeline;

import java.util.GregorianCalendar;

import javax.swing.JFrame;

import model.exceptions.InvalidDateException;
import timeline.Event;
import timeline.ConcreteTimeline;
import timeline.Timeline;

public class GUITest02 {

	public static void main(String[] args) throws InvalidDateException {
		JFrame frame = new JFrame("GUI Test02");
		Timeline timeline = new ConcreteTimeline();
		timeline.addEvent(new Event("Riunione", new GregorianCalendar(2015, 03, 29, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione1", new GregorianCalendar(2015, 02, 30, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione2", new GregorianCalendar(2015, 03, 3, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione3", new GregorianCalendar(2015, 03, 6, 23, 44, 04)));
		timeline.addEvent(new Event("Riunione4", new GregorianCalendar(2015, 04, 25, 23, 44, 04)));
		Drawer drawer = new Drawer(new DrawableTimeline(timeline));
		frame.getContentPane().add(drawer);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
