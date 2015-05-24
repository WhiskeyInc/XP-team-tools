package ui.login;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JCheckBox;

import client.utils.ISessionSaver;

/**
 * This class it's an implementation of @ItemListener and it's used for store 
 * a session, by overriding the itemStateChanged method: if the user has selected
 * the "Remembre me" box, the session is saved
 * 
 * @author pavlo
 *
 */
public class RememberMeListener implements ItemListener {

	private JCheckBox saveCheck;
	private ISessionSaver sessionSaver;
	
	public RememberMeListener(ISessionSaver sessionSaver, JCheckBox saveCheck) {
		this.saveCheck = saveCheck;
		this.sessionSaver = sessionSaver;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//Object source = e.getItemSelectable();

		if (saveCheck.isSelected()) {
			try {
				sessionSaver.saveSession();
				System.out.println("Ciaooo");
			} catch (NoSuchAlgorithmException | IOException e1) {
				
				e1.printStackTrace();
			}
		} else {
			
		}

	}

}
