package tests;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;

import timeline.Event;
import timeline.Timeline;
import filtering.TargetFilter;
import filtering.chechers.TargetPeriodEventChecker;
import filtering.chechers.TargetMembersEventChecker;
import filtering.chechers.TargetNameEventChecker;

public class EventFilteringTests {

	Timeline timeline = new Timeline();

	@Test
	public void DateEventFilterTest() {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("OtherEvent", new GregorianCalendar(2015,
				01, 10, 23, 3, 50)));
		assertEquals(
				1,
				timeline.getEvents(
						new TargetFilter<Event>(new TargetPeriodEventChecker(
								new GregorianCalendar(2015, 01, 01, 23, 3, 50),
								new GregorianCalendar(2015, 01, 11, 23, 3, 50))))
						.size());
	}

	@Test
	public void MemberEventFilter() {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("OtherEvent", new GregorianCalendar(2015,
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
						new TargetFilter<Event>(new TargetMembersEventChecker(
								partecipants))).size());
		assertEquals(
				"Briefing",
				timeline.getEvents(
						new TargetFilter<Event>(new TargetMembersEventChecker(
								partecipants))).get(0).toString());
	}

	@Test
	public void NameEventFilterTest() {
		timeline.addEvent(new Event("Briefing", new GregorianCalendar(2015, 02,
				20, 23, 3, 50)));
		timeline.addEvent(new Event("OtherEvent", new GregorianCalendar(2015,
				01, 10, 23, 3, 50)));
		timeline.addEvent(new Event("SomeEvent somewhere...",
				new GregorianCalendar(2015, 02, 20, 23, 3, 50)));
		assertEquals(
				2,
				timeline.getEvents(
						new TargetFilter<Event>(new TargetNameEventChecker("Event")))
						.size());
		assertEquals(
				"OtherEvent" + "SomeEvent somewhere...",
				timeline.getEvents(
						new TargetFilter<Event>(new TargetNameEventChecker("Event")))
						.get(0).toString()
						+ timeline
								.getEvents(
										new TargetFilter<Event>(
												new TargetNameEventChecker("Event")))
								.get(1).toString());
	}

}
