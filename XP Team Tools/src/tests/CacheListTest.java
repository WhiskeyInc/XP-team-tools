package tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import server.model.CacheList;

public class CacheListTest {

	@Test
	public void testRecoverMessages() throws Exception {
		CacheList cache = new CacheList();
		int numOfMessages = 5;
		int totMessages = 10;
		
		String[] messages = new String[numOfMessages];
		
		for (int i = 0; i < totMessages; i++) {
			cache.storeMessage(null, "" + i);
		}
		for (int i = 5; i < totMessages; i++) {
			messages[i-numOfMessages] = "" + i;
		}
		
		assertArrayEquals(cache.recoverLastMessages(null, numOfMessages), messages);
		numOfMessages = 4;
		messages = new String[numOfMessages];
		for (int i = 6; i < totMessages; i++) {
			messages[i-numOfMessages-2] = "" + i;
		}
		
		assertArrayEquals(cache.recoverLastMessages(null, numOfMessages), messages);
		
	}
}
