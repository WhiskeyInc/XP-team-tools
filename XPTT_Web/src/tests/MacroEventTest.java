package tests;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

import timeline.ConcreteTimeline;
import timeline.MacroEvent;
import util.serialization.SerializerCollector;

public class MacroEventTest {

	@Test
	public void test01() throws Exception {
		ConcreteTimeline timeline = new ConcreteTimeline(
				TimeZone.getTimeZone("Europe/Rome"));
		MacroEvent macroEvent = new MacroEvent("First xTrEAM release",
				new GregorianCalendar(2015, 4, 23), new GregorianCalendar(2015,
						5, 1), timeline);
		assertEquals(1, macroEvent.getEventsNumber());
		assertEquals(ConcreteTimeline.CREATION_EVENT,
				macroEvent.getEvent(SerializerCollector.FIRST_ID).toString());
	}
}
