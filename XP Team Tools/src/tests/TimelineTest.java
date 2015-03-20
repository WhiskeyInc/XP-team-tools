package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Event;
import model.Timeline;

import org.junit.Test;

public class TimelineTest {

	Timeline timeline = new Timeline();

	@Test
	public void timelineCreationTest() {
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void addEventTest() {
		timeline.addEvent(new Event("Briefing", "20 03 2015"));
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void dropEventTest() {
		timeline.addEvent(new Event("Briefing", "23 02 2014"));
		timeline.dropEvent("Briefing");
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void dateDisplayTest() {
		timeline.addEvent(new Event("Briefing", "23 02 2014"));
		assertEquals("23 02 2014", timeline.getEvent("Briefing").getDate());
	}

	@Test
	public void creationEventTest() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String creationDate = format.format(cal.getTime());
		assertEquals(creationDate, timeline.getEvent("creation").getDate());
	}

	@Test
	public void timeChangeTest() {
		timeline.addEvent(new Event("Riunione sulla timeline", "20 03 2015"));
		timeline.moveEvent("Riunione sulla timeline", "21 03 2015");
		assertEquals("21 03 2015",
				timeline.getEvent("Riunione sulla timeline").getDate());
	}

	@Test
	public void participantAdditionTest() throws Exception {
		timeline.addEvent(new Event("Briefing", "20 03 2015"));
		timeline.getEvent("Briefing").addParticipant("Simone Colucci");
		assertTrue(timeline.getEvent("Briefing").getParticipants().contains(
				"Simone Colucci"));
	}

}
