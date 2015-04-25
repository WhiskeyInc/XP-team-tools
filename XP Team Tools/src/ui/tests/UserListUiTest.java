package ui.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ui.UserListUI;

public class UserListUiTest {

	@Test
	public void test01() {
		ArrayList<String> nicknames = new ArrayList<String>();
		nicknames.add("Cesare");
		UserListUI Ui = new UserListUI(nicknames);
		assertEquals(Ui.getNicknames(), nicknames);
		assertEquals(Ui.getNicknames().get(0), "Cesare");
		
		
	}
	
	@Test
	public void test02() {
		ArrayList<String> nicknames = new ArrayList<String>();
		nicknames.add("Cesare");
		UserListUI Ui = new UserListUI(nicknames);
		Ui.getBox().get(0).doClick();
		assertEquals(Ui.getBox().get(0).isSelected(), true);
	}
	
	@Test
	public void test03() {
		ArrayList<String> nicknames = new ArrayList<String>();
		nicknames.add("Cesare");
		nicknames.add("Alessia Montanini");
		nicknames.add("Defo");
		UserListUI Ui = new UserListUI(nicknames);
		Ui.getBox().get(0).doClick();
		Ui.getBox().get(2).doClick();
		Ui.getButton().doClick();
		assertEquals(Ui.getSelectedNicknames().get(0), "Cesare");
		assertEquals(Ui.getSelectedNicknames().get(1), "Defo");
		
		
	}

}
