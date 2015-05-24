package client.utils;

import javax.swing.JOptionPane;

public class ErrorMessage {

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Ops, something went wrong: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
