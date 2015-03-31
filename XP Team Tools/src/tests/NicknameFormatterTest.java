package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import string.formatter.NickNameFormatter;

public class NicknameFormatterTest {

	@Test
	public void testNickName() throws Exception {
		assertEquals(NickNameFormatter.formatNickname("LuBardo"), "[LuBardo]:");
	}

}
