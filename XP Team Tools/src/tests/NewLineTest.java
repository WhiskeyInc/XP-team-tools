package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import string.formatter.NewLineMaker;

public class NewLineTest {

	@Test
	public void testNewLineMaker() throws Exception {
		
		assertEquals("Ciao!\n", NewLineMaker.appendNewLine("Ciao!") );
	}

}
