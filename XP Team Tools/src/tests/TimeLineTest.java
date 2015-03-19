package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Timeline;

import org.junit.Test;

public class TimeLineTest {

	Timeline timeline = new Timeline();

	@Test
	public void timelineCreationTest() {
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void newEventTest() {
		timeline.newEvent(null, null);
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void dropEventTest() {
		timeline.newEvent("Briefing", "23 02 2014");
		timeline.dropEvent("Briefing");
		assertEquals(1, timeline.getEventsNumber());
	}

	@Test
	public void dateDisplayTest() {
		timeline.newEvent("Riunione sulla timeline", "20 03 2015");
		assertEquals("20 03 2015",
				timeline.getEventDate("Riunione sulla timeline"));
	}
	
	@Test
	public void creationEventTest() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String creationDate = format.format(cal.getTime());
		assertEquals(creationDate, timeline.getEventDate("creation"));
	}

}
