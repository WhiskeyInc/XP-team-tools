package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class LoadingPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JLabel label = new JLabel();
	
	public LoadingPanel(String text) {
		
		setLayout(new GridLayout(15,1));
		
		label.setText(text);
		
		super.setBackground(Color.WHITE);

		for (int i = 0; i < 14; i++) {
			add(new JTextArea(""));
		}
		add(label);
	
		
		
	}
	
	public void setLabelText(String text) {
		label.setText(text);
	}
	
	public String getLabelText() {
		return label.getText();
	}
	
	

}
