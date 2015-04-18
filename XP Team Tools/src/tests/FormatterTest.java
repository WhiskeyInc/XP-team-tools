package tests;

import static org.junit.Assert.*;

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

	@Test
	public void testDistinguishableMessage() throws Exception {
		assertEquals(Formatter.markMessage("Ciao"), "Ciao" + Formatter.SECRET_CODE);
	}
	
	@Test
	public void testRemoveSecretCode() throws Exception {
		assertEquals("Ciao", Formatter.removeSecretCode("Ciao"+Formatter.SECRET_CODE));
	}
	
	@Test
	public void testName() throws Exception {
		String str = "Numero@#°akff";
		System.out.println(str);
		String[] string = str.split("@#°");
		System.out.println(string[0]);
		System.out.println(string[1]);

	}
	
	@Test
	public void testContains() throws Exception {
		String hidden = Formatter.markMessage("Ciao");
		assertTrue(hidden.contains(Formatter.SECRET_CODE));
		assertFalse(hidden.contains("@#C"));
	}
}
