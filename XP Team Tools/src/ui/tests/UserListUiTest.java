package ui.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ui.UserListUIBox;

public class UserListUiTest {

	@Test
	public void test01() {
		String[] nicks = new String[1];
		nicks[0] = "Cesare";
		UserListUIBox Ui = new UserListUIBox(nicks);
		assertArrayEquals(Ui.getNicknames(), nicks);
		assertEquals(Ui.getNicknames()[0], "Cesare");
		
		
	}
	
	@Test
	public void test02() {
		String[] nicknames = new String[1];
		nicknames[0] = "Cesare";
		UserListUIBox Ui = new UserListUIBox(nicknames);
		Ui.getBox().get(0).doClick();
		assertEquals(Ui.getBox().get(0).isSelected(), true);
	}
	
	@Test
	public void test03() {
		String[] nicknames = new String[3];
		nicknames[0] = "Cesare";
		nicknames[1] = "Alessia Montanini";
		nicknames[2] = "Defo";
		UserListUIBox Ui = new UserListUIBox(nicknames);
		Ui.getBox().get(0).doClick();
		Ui.getBox().get(2).doClick();
		Ui.getButton().doClick();
		assertEquals(Ui.getListOfSelectedNicknames().get(0), "Cesare");
		assertEquals(Ui.getListOfSelectedNicknames().get(1), "Defo");
		
		
	}

}
