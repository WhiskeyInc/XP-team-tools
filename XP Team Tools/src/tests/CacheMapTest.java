package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.CacheMap;
import server.model.NoMessagesException;

public class CacheMapTest {

	@Test
	public void test() throws NoMessagesException {

		CacheMap cache = new CacheMap();
		int numOfMessages = 5;
		int totMessages = 10;

		String[] messages = new String[numOfMessages];

		for (int i = 0; i < totMessages; i++) {
			cache.storeMessage("prova", "" + i);
		}
		for (int i = 5; i < totMessages; i++) {
			messages[i - numOfMessages] = "" + i;
		}

		assertArrayEquals(cache.recoverLastMessages("prova", numOfMessages),
				messages);
		numOfMessages = 4;
		messages = new String[numOfMessages];
		for (int i = 6; i < totMessages; i++) {
			messages[i - numOfMessages - 2] = "" + i;
		}

		assertArrayEquals(cache.recoverLastMessages("prova", numOfMessages),
				messages);
	}

}
