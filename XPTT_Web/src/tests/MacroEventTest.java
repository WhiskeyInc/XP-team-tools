package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import model.exceptions.InvalidDateException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.MacroEvent;
import util.serialization.SerializerCollector;

public class MacroEventTest {

	ConcreteTimeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"));

	@Test
	public void constructorTest() {
		try {
			MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
					new GregorianCalendar(1990, 3, 23), new GregorianCalendar(2020, 5,
							2), timeline);
			fail();
		} catch (InvalidDateException e) {
			assertEquals(1, 1);
		}
		try {
			MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
					new GregorianCalendar(2020, 4, 23), new GregorianCalendar(2020, 4,
							2), timeline);
			fail();
		} catch (InvalidDateException e) {
			assertEquals(1, 1);
		}
	}
	
	@Test
	public void creationTest() throws Exception {
		MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
				new GregorianCalendar(2020, 4, 23), new GregorianCalendar(2020, 5,
						2), timeline);
		assertEquals(1, macroEvent.getEventsNumber());
		assertEquals(ConcreteTimeline.DEFAULT_CREATION_EVENT, macroEvent
				.getEvent(SerializerCollector.FIRST_ID).toString());
	}

	@Test
	public void dateTest() throws InvalidDateException {
		MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
				new GregorianCalendar(2020, 4, 23), new GregorianCalendar(2020, 5,
						2), timeline);
		try {
			macroEvent.addEvent(new Event("Compleanno Luca",
					new GregorianCalendar(2020, 5, 1)));
			assertEquals(1, 1);
		} catch (InvalidDateException e) {
			fail();	
		}
		try {
			macroEvent.addEvent(new Event("Compleanno Motta",
					new GregorianCalendar(2020, 5, 24)));
			fail();
		} catch (InvalidDateException e) {
			assertEquals(1, 1);
		}
		try {
			macroEvent.addEvent(new Event("Compleanno Simo", new GregorianCalendar(
					2019, 11, 24)));
			fail();
		} catch (InvalidDateException e) {
			assertEquals(1, 1);
		}
	}
}
