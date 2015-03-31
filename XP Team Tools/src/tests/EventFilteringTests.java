package tests;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import model.exceptions.InvalidDateException;
import model.exceptions.NoSuchEventException;

import org.junit.Test;

import timeline.Event;
import timeline.ConcreteTimeline;
import timeline.Timeline;
import filtering.TargetFilter;
import filtering.checkers.NameEventChecker;
import filtering.checkers.ParticipantsEventChecker;
import filtering.checkers.PeriodEventChecker;

public class EventFilteringTests {

	Timeline timeline = new ConcreteTimeline();

	@Test
	public void DateEventFilterTest() throws InvalidDateException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("OtherEvent", new GregorianCalendar(2020,
				01, 10, 23, 3, 50)));
		assertEquals(
				1,
				timeline.getEvents(
						new TargetFilter<Event>(new PeriodEventChecker(
								new GregorianCalendar(2020, 01, 01, 23, 3, 50),
								new GregorianCalendar(2020, 01, 11, 23, 3, 50))))
						.size());
	}

	@Test
	public void MemberEventFilter() throws InvalidDateException, NoSuchEventException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("OtherEvent", new GregorianCalendar(2020,
				01, 10, 23, 3, 50)));
		String[] partecipants = new String[2];
		partecipants[0] = "Simone";
		partecipants[1] = "Alessandro";
		timeline.getEvent("Briefing").addParticipant("Simone");
		timeline.getEvent("Briefing").addParticipant("Alessandro");
		timeline.getEvent("OtherEvent").addParticipant("Simone");
		assertEquals(
				1,
				timeline.getEvents(
						new TargetFilter<Event>(new ParticipantsEventChecker(
								partecipants))).size());
		assertEquals(
				"Briefing",
				timeline.getEvents(
						new TargetFilter<Event>(new ParticipantsEventChecker(
								partecipants))).get(0).toString());
	}

	@Test
	public void NameEventFilterTest() throws InvalidDateException {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2020, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("OtherEvent", new GregorianCalendar(2020,
				01, 10, 23, 3, 50)));
		timeline.addEvent(new Event("SomeEvent somewhere...",
				new GregorianCalendar(2020, 02, 20, 23, 3, 50)));
		assertEquals(
				2,
				timeline.getEvents(
						new TargetFilter<Event>(new NameEventChecker("Event")))
						.size());
		assertEquals(
				"OtherEvent" + "SomeEvent somewhere...",
				timeline.getEvents(
						new TargetFilter<Event>(new NameEventChecker("Event")))
						.get(0).toString()
						+ timeline
								.getEvents(
										new TargetFilter<Event>(
												new NameEventChecker("Event")))
								.get(1).toString());
	}

}
