package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import org.junit.Test;

import timeline.Event;
import timeline.Timeline;
import filtering.NoFilter;

public class TimelineTest {

	Timeline timeline = new Timeline();

	@Test
	public void timelineCreationTest() {
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void addEventTest() {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
				20, 23, 3, 50)));
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void dropEventTest() {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
				20, 23, 3, 50)));
		timeline.dropEvent("Briefing");
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void dateDisplayTest() {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
				20, 23, 3, 50)));
		assertEquals(new GregorianCalendar(2015, 02, 20, 23, 3, 50), timeline
				.getEvent("Briefing").getDate());
	}

	// @Test
	/*
	 * May fail because of computing dalays
	 */
	// public void creationEventTest() {
	// Calendar cal = (GregorianCalendar) Calendar.getInstance();
	// assertEquals(cal, timeline.getEvent("creation").getDate());
	// }

	@Test
	public void timeChangeTest() {
		timeline.addEvent(new Event("Riunione sulla timeline",
				new GregorianCalendar(2015, 02, 20, 23, 3, 50)));
		timeline.moveEvent("Riunione sulla timeline", new GregorianCalendar(
				2015, 02, 20, 23, 3, 50));
		assertEquals(new GregorianCalendar(2015, 02, 20, 23, 3, 50), timeline
				.getEvent("Riunione sulla timeline").getDate());
	}

	@Test
	public void participantAdditionTest() throws Exception {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
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
}
