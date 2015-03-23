package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimelineIntegrationTest {

	Tool tool = new Tool();
	
	@Test
	public void test() {
		tool.addTask("Nuovo task", "Integrare task in timeline");
		assertEquals(2, tool.getEventsNumber());		
	}

}
