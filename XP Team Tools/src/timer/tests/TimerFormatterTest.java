package timer.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import timer.TimerFormatter;

public class TimerFormatterTest {

	@Test
	public void test() {
		assertEquals(TimerFormatter.getDisplay(8, 20), "08 : 20");
		assertEquals(TimerFormatter.getDisplay(1, 40), "01 : 40");
		assertEquals(TimerFormatter.getDisplay(0, 10), "00 : 10");
		assertTrue(TimerFormatter.isTimeStampValid("23:52"));
		assertTrue(TimerFormatter.isTimeStampValid("00:00"));
		assertFalse(TimerFormatter.isTimeStampValid("f:00"));
		assertFalse(TimerFormatter.isTimeStampValid("00:80"));
		
	}
	

}
