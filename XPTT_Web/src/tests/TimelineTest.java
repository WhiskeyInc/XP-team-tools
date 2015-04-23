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
import filtering.NoFilter;

public class TimelineTest {

	Timeline timeline = new ConcreteTimeline(
			TimeZone.getTimeZone("Europe/Rome"));

	@Test
	public void timelineCreationTest() {
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void addEventTest() throws InvalidDateException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2050, 12,
				22, 13, 13, 13), true));
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void deleteEventTest() throws InvalidDateException,
			NoSuchEventException, UnEditableEventException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2050, 12,
				22, 13, 13, 13), true));
		timeline.deleteEvent(2);
		assertEquals(1, timeline.getEventsNumber());
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
		timeline.addEvent(new Event("Riunione sulla timeline",
				new GregorianCalendar(2020, 02, 20, 23, 3, 50), true));
		timeline.moveEvent(1, new GregorianCalendar(2020, 02, 20, 23, 3, 50));
		assertEquals(new GregorianCalendar(2020, 02, 20, 23, 3, 50), timeline
				.getEvent(1).getDate());
	}

	@Test
	public void participantAdditionTest() throws Exception {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50), true));
		timeline.getEvent(1).addParticipant("Simone Colucci");
		assertTrue(timeline.getEvent(1).getParticipants()
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
			assertTrue(true);
		}
		try {
			timeline.addEvent(new Event("Test", new GregorianCalendar(2020, 01,
					29, 02, 02, 02), true));
			assertTrue(true);
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
			assertTrue(true);
		}
		try {
			timeline.deleteEvent(3);
			fail();
		} catch (NoSuchEventException e) {
			assertTrue(true);
		}
		try {
			timeline.moveEvent(3, new GregorianCalendar(2020, 1, 1, 1, 1, 1));
			fail();
		} catch (NoSuchEventException e) {
			assertTrue(true);
		}

		timeline.addEvent(new Event("Esisto", new GregorianCalendar(2020, 1, 1,
				1, 1, 1), true));
		try {
			timeline.getEvent(1);
			assertTrue(true);
		} catch (NoSuchEventException e) {
			fail();
		}
		try {
			timeline.moveEvent(1, new GregorianCalendar(2020, 1, 1, 1, 1, 1));
			assertTrue(true);
		} catch (NoSuchEventException e) {
			fail();
		}
		try {
			timeline.deleteEvent(1);
			assertTrue(true);
		} catch (NoSuchEventException e) {
			fail();
		}
	}

	@Test
	public void unMovableEventTest() throws InvalidDateException,
			NoSuchEventException {
		timeline.addEvent(new Event("Timeline", new GregorianCalendar(2020, 2,
				2, 2, 2, 2), true));
		try {
			timeline.moveEvent(0, new GregorianCalendar(2020, 2, 2, 2, 2, 2));
			fail();
		} catch (UnEditableEventException e) {
			assertEquals(1, 1);
		}
		try {
			timeline.moveEvent(1, new GregorianCalendar(2030, 3, 3, 3, 3, 3));
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
