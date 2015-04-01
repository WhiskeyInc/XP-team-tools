package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;
import model.exceptions.UnmovableEventException;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import filtering.NoFilter;

public class TimelineTest {

	Timeline timeline = new ConcreteTimeline();

	@Test
	public void timelineCreationTest() {
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void addEventTest() throws InvalidDateException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void deleteEventTest() throws InvalidDateException,
			NoSuchEventException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		timeline.deleteEvent("Briefing");
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void dateDisplayTest() throws InvalidDateException,
			NoSuchEventException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		assertEquals(new GregorianCalendar(2020, 02, 20, 23, 3, 50), timeline
				.getEvent("Briefing").getDate());
	}

	@Test
	public void timeChangeTest() throws InvalidDateException,
			NoSuchEventException, UnmovableEventException {
		timeline.addEvent(new Event("Riunione sulla timeline",
				new GregorianCalendar(2020, 02, 20, 23, 3, 50)));
		timeline.moveEvent("Riunione sulla timeline", new GregorianCalendar(
				2020, 02, 20, 23, 3, 50));
		assertEquals(new GregorianCalendar(2020, 02, 20, 23, 3, 50), timeline
				.getEvent("Riunione sulla timeline").getDate());
	}

	@Test
	public void participantAdditionTest() throws Exception {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		timeline.getEvent("Briefing").addParticipant("Simone Colucci");
		assertTrue(timeline.getEvent("Briefing").getParticipants()
				.contains("Simone Colucci"));
	}

	@Test
	public void getEventsOrderedTest() throws Exception {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2035, 05,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("Setting", new GregorianCalendar(2035, 05,
				20, 22, 3, 50)));
		timeline.addEvent(new Event("Meeting", new GregorianCalendar(2040, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("Something else...", new GregorianCalendar(
				2030, 03, 30, 23, 3, 50)));
		assertEquals("creation" + "Something else..." + "Setting" + "Briefing"
				+ "Meeting", timeline.getEvents(new NoFilter<Event>()).get(0)
				.toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(1).toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(2).toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(3).toString()
				+ timeline.getEvents(new NoFilter<Event>()).get(4).toString());
	}

	@Test
	public void noDateBeforeCreation() {
		try {
			timeline.addEvent(new Event("Test", new GregorianCalendar(2015, 01,
					29, 02, 02, 02)));
			fail();
		} catch (InvalidDateException e) {
			assertTrue(true);
		}
		try {
			timeline.addEvent(new Event("Test", new GregorianCalendar(2020, 01,
					29, 02, 02, 02)));
			assertTrue(true);
		} catch (InvalidDateException e) {
			fail();
		}
	}

	@Test
	public void noSuchEventTest() throws Exception {
		try {
			timeline.getEvent("Non esisto");
			fail();
		} catch (NoSuchEventException e) {
			assertTrue(true);
		}
		try {
			timeline.deleteEvent("Non esisto");
			fail();
		} catch (NoSuchEventException e) {
			assertTrue(true);
		}
		try {
			timeline.moveEvent("Non esisto", new GregorianCalendar(2020, 1, 1,
					1, 1, 1));
			fail();
		} catch (NoSuchEventException e) {
			assertTrue(true);
		}

		timeline.addEvent(new Event("Esisto", new GregorianCalendar(2020, 1, 1,
				1, 1, 1)));
		try {
			timeline.getEvent("Esisto");
			assertTrue(true);
		} catch (NoSuchEventException e) {
			fail();
		}
		try {
			timeline.moveEvent("Esisto", new GregorianCalendar(2020, 1, 1, 1,
					1, 1));
			assertTrue(true);
		} catch (NoSuchEventException e) {
			fail();
		}
		try {
			timeline.deleteEvent("Esisto");
			assertTrue(true);
		} catch (NoSuchEventException e) {
			fail();
		}
	}

	@Test
	public void unMovableEventTest() throws InvalidDateException,
			NoSuchEventException {
		timeline.addEvent(new Event("Timeline", new GregorianCalendar(2020, 2,
				2, 2, 2, 2)));
		try {
			timeline.moveEvent("creation", new GregorianCalendar(2020, 2, 2, 2,
					2, 2));
			fail();
		} catch (UnmovableEventException e) {
			assertEquals(1, 1);
		}
		try {
			timeline.moveEvent("Timeline", new GregorianCalendar(2030, 3, 3, 3,
					3, 3));
		} catch (UnmovableEventException e) {
			fail();
		}
	}

	@Test
	public void eventComparationTest() throws Exception {
		timeline.addEvent(new Event("Terzo", new GregorianCalendar(2020, 2, 2,
				2, 2, 2)));
		timeline.addEvent(new Event("Secondo", new GregorianCalendar(2020, 2,
				2, 2, 2, 1)));
		assertEquals("Secondo" + "Terzo",
				timeline.getEvents(new NoFilter<Event>()).get(1).toString()
						+ timeline.getEvents(new NoFilter<Event>()).get(2)
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
