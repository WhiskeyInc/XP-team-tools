package ui.tests;

import ui.CopyOfUI;

public class CopyOfUiTest {
	public static void main(String[] args) {
		CopyOfUI ui = new CopyOfUI();
		String[] membs = new String[3];
		membs[0] = "IO";
		membs[1] = "Tu";
		membs[2] = "Esso";
		
		ui.setMembersList(membs);
		ui.refresh();
		
		long time = System.currentTimeMillis() + 5000;
		
		while(System.currentTimeMillis() < time) {

		}
		membs = new String[7];
		membs[0] = "IO";
		membs[1] = "Tu";
		membs[2] = "Esso";
		membs[3] = "Anche io";
		membs[4] = "Sono";
		membs[5] = "Un membro";
		membs[6] = "Del team";
		ui.setMembersList(membs);
		ui.refresh();
	}

}
