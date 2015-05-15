package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import string.formatter.Formatter;

public class FormatterTest {

	@Test
	public void testNickName() throws Exception {
		assertEquals(Formatter.formatNickname("LuBardo"), "[LuBardo]:");
		
		assertEquals(Formatter.formatNickname("LuBardo"), "[LuBardo]:");
	}
	
	@Test
	public void testNewLineMaker() throws Exception {
		
		assertEquals("Ciao!\n", Formatter.appendNewLine("Ciao!") );
	}

}
