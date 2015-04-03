package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import string.formatter.Formatter;
import string.formatter.IStringFormatter;
import string.formatter.NickNameFormatter;

public class NicknameFormatterTest {

	@Test
	public void testNickName() throws Exception {
		assertEquals(NickNameFormatter.formatNickname("LuBardo"), "[LuBardo]:");
		
		IStringFormatter formatter = new Formatter();
		assertEquals(formatter.formatMessage("LuBardo"), "[LuBardo]:");
	}

}
