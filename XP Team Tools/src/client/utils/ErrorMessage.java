package client.utils;

import javax.swing.JOptionPane;

/**
 * This class contains a method that shows a error message if login operation goes wrong
 * 
 * @author pavlo
 *
 */
public class ErrorMessage {

	/**
	 * shows a message dialog if the login goes wrong
	 * @param infoMessage
	 * @param titleBar
	 */
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Ops, something went wrong: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
