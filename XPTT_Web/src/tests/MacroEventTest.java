package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import model.exceptions.InvalidDateException;
import model.exceptions.UnEditableEventException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.events.MacroEvent;
import util.serialization.LocalIdentifiabilitySerializer;
import util.serialization.SerializerCollector;

public class MacroEventTest {

	ConcreteTimeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"),
			new LocalIdentifiabilitySerializer());

	@Test
	@SuppressWarnings("unused")
	public void constructorTest() {
		try {
			MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
					new GregorianCalendar(1990, 3, 23), new GregorianCalendar(
							2020, 5, 2), timeline);
			fail();
		} catch (InvalidDateException e) {
		}
		try {
			MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
					new GregorianCalendar(2020, 4, 23), new GregorianCalendar(
							2020, 4, 2), timeline);
			fail();
		} catch (InvalidDateException e) {
		}
	}

	@Test
	public void creationTest() throws Exception {
		MacroEvent macroEvent = createEvent();
		assertEquals(1, macroEvent.getEventsNumber());
		assertEquals(ConcreteTimeline.DEFAULT_CREATION_EVENT, macroEvent
				.getEvent(SerializerCollector.FIRST_ID).toString());
	}

	@Test
	public void dateTest() throws InvalidDateException {
		MacroEvent macroEvent = createEvent();
		try {
			macroEvent.addEvent(new Event("Compleanno Luca",
					new GregorianCalendar(2020, 5, 1)));
		} catch (InvalidDateException e) {
			fail();
		}
		try {
			macroEvent.addEvent(new Event("Compleanno Motta",
					new GregorianCalendar(2020, 5, 24)));
			fail();
		} catch (InvalidDateException e) {
		}
		try {
			macroEvent.addEvent(new Event("Compleanno Simo",
					new GregorianCalendar(2019, 11, 24)));
			fail();
		} catch (InvalidDateException e) {
		}
	}

	@Test
	public void unEditableMicroEventTest() throws Exception {
		MacroEvent macroEvent = this.createEvent();
		macroEvent.addEvent(new Event("Compleanno Simo", new GregorianCalendar(
				2020, 4, 25)));
		try {
			macroEvent.moveEvent(1, new GregorianCalendar(2020, 5, 1));
			fail();
		} catch (UnEditableEventException e) {
		}
	}

	@Test
	public void dateChangingSuccess() throws Exception {
		MacroEvent macroEvent = this.createEvent();
		macroEvent.addEvent(new Event("test",
				new GregorianCalendar(2020, 4, 28)));
		macroEvent.setDate(new GregorianCalendar(2020, 5, 10));
	}

	@Test
	public void dateChangingFailure() throws Exception {
		MacroEvent macroEvent = this.createEvent();
		macroEvent.addEvent(new Event("test",
				new GregorianCalendar(2020, 4, 28)));
		try {
			macroEvent.setDate(new GregorianCalendar(2020, 4, 27));
			fail();
		} catch (InvalidDateException e) {
		}
	}

	@Test
	public void dateChangingFailure2() throws Exception {
		MacroEvent macroEvent = createEvent();
		try {
			macroEvent.setDate(new GregorianCalendar(2019, 4, 23));
			fail();
		} catch (InvalidDateException exception) {
		}
	}

	private MacroEvent createEvent() throws InvalidDateException {
		MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
				new GregorianCalendar(2020, 4, 23), new GregorianCalendar(2020,
						5, 2), timeline);
		return macroEvent;
	}
}
