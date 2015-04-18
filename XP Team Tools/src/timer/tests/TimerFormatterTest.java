package timer.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import timer.TimerFormatter;

public class TimerFormatterTest {

	@Test
	public void test() {
		assertEquals(TimerFormatter.getTimeStamp(500000), "08 : 20");
		assertEquals(TimerFormatter.getTimeStamp(100000), "01 : 40");
		assertEquals(TimerFormatter.getTimeStamp(10000), "00 : 10");
		
	}
	

}
