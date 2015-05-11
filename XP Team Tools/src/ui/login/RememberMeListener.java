package ui.login;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class RememberMeListener implements ItemListener {

	private JCheckBox saveCheck;
	//private SessionSaver sessionSaver = new SessionSaver();
	
	public RememberMeListener(JCheckBox saveCheck) {
		this.saveCheck = saveCheck;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		if (source == saveCheck) {
			//sessionsSaver.saveSession();
		} else {
			//sessionSaver.deleteSession();
		}

	}

}
