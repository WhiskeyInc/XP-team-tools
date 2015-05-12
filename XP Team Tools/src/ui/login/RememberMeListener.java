package ui.login;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JCheckBox;

import server.utils.ISessionSaver;

public class RememberMeListener implements ItemListener {

	private JCheckBox saveCheck;
	private ISessionSaver sessionSaver;
	
	public RememberMeListener(ISessionSaver sessionSaver, JCheckBox saveCheck) {
		this.saveCheck = saveCheck;
		this.sessionSaver = sessionSaver;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		if (saveCheck.isSelected()) {
			try {
				sessionSaver.saveSession();
				System.out.println("Ciaooo");
			} catch (NoSuchAlgorithmException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			//sessionSaver.deleteSession();
		}

	}

}
