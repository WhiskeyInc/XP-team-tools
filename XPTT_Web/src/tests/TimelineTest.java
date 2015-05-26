package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnEditableEventException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import util.serialization.LocalIdentifiabilitySerializer;
import util.serialization.SerializerCollector;
import filtering.NoFilter;

public class TimelineTest {

	Timeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"),
			new LocalIdentifiabilitySerializer());

	@Test
	public void timelineCreationTest() throws NoSuchEventException {
		assertEquals(
				timeline.getEvent(SerializerCollector.FIRST_ID).toString(),
				ConcreteTimeline.DEFAULT_CREATION_EVENT);
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void addEventTest() throws InvalidDateException,
			NoSuchEventException {
		Event event = new Event("Briefing", new GregorianCalendar(2050, 12, 22,
				13, 13, 13), true);
		timeline.addEvent(event);
		assertEquals(event, timeline.getEvent(event.getId()));
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void deleteEventTest() throws InvalidDateException,
			NoSuchEventException, UnEditableEventException {
		Event event = new Event("Briefing", new GregorianCalendar(2050, 12, 22,
				13, 13, 13), true);
		timeline.addEvent(event);
		timeline.deleteEvent(event.getId());
		assertEquals(1, timeline.getEventsNumber());
		assertEquals(ConcreteTimeline.DEFAULT_CREATION_EVENT, timeline
				.getEvent(SerializerCollector.FIRST_ID).toString());
	}

	@Test
	public void dateDisplayTest() throws InvalidDateException,
			NoSuchEventException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2050, 12,
				22, 13, 13, 13), true));
		assertEquals(new GregorianCalendar(2050, 12, 22, 13, 13, 13), timeline
				.getEvent(1).getDate());
	}

	@Test
	public void timeChangeTest() throws InvalidDateException,
			NoSuchEventException, UnEditableEventException {
		Event event = new Event("Riunione sulla timeline",
				new GregorianCalendar(2020, 02, 20, 23, 3, 50), true);
		timeline.addEvent(event);
		timeline.moveEvent(event.getId(), new GregorianCalendar(2020, 02, 20,
				23, 3, 50));
		assertEquals(new GregorianCalendar(2020, 02, 20, 23, 3, 50), timeline
				.getEvent(event.getId()).getDate());
	}

	@Test
	public void participantAdditionTest() throws Exception {
		Event event = new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50), true);
		timeline.addEvent(event);
		timeline.getEvent(event.getId()).addParticipant("Simone Colucci");
		assertTrue(timeline.getEvent(event.getId()).getParticipants()
				.contains("Simone Colucci"));
	}

	@Test
	public void getEventsOrderedTest() throws Exception {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2035, 05,
				20, 23, 3, 50), true));
		timeline.addEvent(new Event("Setting", new GregorianCalendar(2035, 05,
				20, 22, 3, 50), true));
		timeline.addEvent(new Event("Meeting", new GregorianCalendar(2040, 02,
				20, 23, 3, 50), true));
		timeline.addEvent(new Event("Something else...", new GregorianCalendar(
				2030, 03, 30, 23, 3, 50), true));
		assertEquals("creation" + "Something else..." + "Setting" + "Briefing"
				+ "Meeting", timeline.getEvents(new NoFilter<Event>()).get(4)
				.toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(3).toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(2).toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(1).toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(0).toString());
	}

	@Test
	public void noDateBeforeCreation() {
		try {
			timeline.addEvent(new Event("Test", new GregorianCalendar(2015, 01,
					29, 02, 02, 02), true));
			fail();
		} catch (InvalidDateException e) {
		}
		try {
			timeline.addEvent(new Event("Test", new GregorianCalendar(2020, 01,
					29, 02, 02, 02), true));
		} catch (InvalidDateException e) {
			fail();
		}
	}

	@Test
	public void noSuchEventTest() throws Exception {
		try {
			timeline.getEvent(3);
			fail();
		} catch (NoSuchEventException e) {
		}
		try {
			timeline.deleteEvent(3);
			fail();
		} catch (NoSuchEventException e) {
		}
		try {
			timeline.moveEvent(3, new GregorianCalendar(2020, 1, 1, 1, 1, 1));
			fail();
		} catch (NoSuchEventException e) {
		}

		Event event = new Event("Esisto", new GregorianCalendar(2020, 1, 1,
				1, 1, 1), true);
		timeline.addEvent(event);
		try {
			timeline.getEvent(event.getId());
		} catch (NoSuchEventException e) {
			fail();
		}
		try {
			timeline.moveEvent(event.getId(), new GregorianCalendar(2020, 1, 1, 1, 1, 1));
		} catch (NoSuchEventException e) {
			fail();
		}
		try {
			timeline.deleteEvent(event.getId());
		} catch (NoSuchEventException e) {
			fail();
		}
	}

	@Test
	public void unEditableEventTest() throws InvalidDateException,
			NoSuchEventException {
		Event event = new Event("Timeline", new GregorianCalendar(2020, 2,
				2, 2, 2, 2), true);
		timeline.addEvent(event);
		try {
			timeline.moveEvent(SerializerCollector.FIRST_ID,
					new GregorianCalendar(2020, 2, 2, 2, 2, 2));
			fail();
		} catch (UnEditableEventException e) {
		}
		try {
			timeline.moveEvent(event.getId(), new GregorianCalendar(2030, 3, 3, 3, 3, 3));
		} catch (UnEditableEventException e) {
			fail();
		}
	}

	@Test
	public void eventComparationTest() throws Exception {
		timeline.addEvent(new Event("Terzo", new GregorianCalendar(2020, 2, 2,
				2, 2, 2), true));
		timeline.addEvent(new Event("Secondo", new GregorianCalendar(2020, 2,
				2, 2, 1, 1), true));
		assertEquals("Terzo" + "Secondo",
				timeline.getEvents(new NoFilter<Event>()).get(0).toString()
						+ timeline.getEvents(new NoFilter<Event>()).get(1)
								.toString());
	}

	// @Test
	/*
	 * this test is commented because since latest version date is computed with
	 * the precision of milliseconds so that this test may fail
	 */
	// public void creationEventTest() {
	// Calendar cal = (GregorianCalendar) Calendar.getInstance();
	// assertEquals(cal, timeline.getEvent("creation").getDate());
	// }
}
