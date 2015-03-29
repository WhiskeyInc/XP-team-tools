package gui;

import gui.drawables.DrawableTimeline;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import timeline.Event;
import timeline.Timeline;

public class GUITest02 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("GUI Test02");
		Timeline timeline = new Timeline();
		timeline.addEvent(new Event("Riunione", (GregorianCalendar) Calendar
				.getInstance()));
		timeline.addEvent(new Event("Riunione2", (GregorianCalendar) Calendar
				.getInstance()));
		timeline.addEvent(new Event("Riunione3", (GregorianCalendar) Calendar
				.getInstance()));
		timeline.addEvent(new Event("Riunione nella tana del topo",
				(GregorianCalendar) Calendar.getInstance()));
		timeline.addEvent(new Event(
				"sacjbd.kfvbdof ghklvjbfdkghskvjbfkksjbvkdfkdjbvkjdvkfjvbkdjvhfdkvbjdk",
				(GregorianCalendar) Calendar.getInstance()));
		Drawer drawer = new Drawer(new DrawableTimeline(timeline));
		frame.getContentPane().add(drawer);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
