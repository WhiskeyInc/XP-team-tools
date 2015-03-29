package gui;

import gui.drawables.DrawableEvent;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import timeline.Event;

public class GUITest {

	public static void main(String[] args) {
		JFrame frame = new JFrame("GUI Test");
		Drawer drawer = new Drawer(new DrawableEvent(new Event("primo test",
				(GregorianCalendar) Calendar.getInstance())));
		frame.getContentPane().add(drawer);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
