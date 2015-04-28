package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UserListUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<JCheckBox> box = new ArrayList<JCheckBox>();
	private ArrayList<String> nicknames;
	private JButton button = new JButton("Start chat");
	private ArrayList<String> selectedNicknames = new ArrayList<String>();

	public UserListUI(final ArrayList<String> nicknames) {
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(nicknames.size(),1));
		this.nicknames = nicknames;
		for (int i = 0; i < nicknames.size(); i++) {
			box.add(new JCheckBox(nicknames.get(i)));
			final int index = i;
			box.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (box.get(index).isSelected()) {
						box.get(index).setForeground(Color.GREEN);
					} else {
						box.get(index).setForeground(Color.BLACK);
					}
				}
			});
			panel1.add(box.get(i));
		}
		JScrollPane pane = new JScrollPane(panel1);
		super.add(pane);
		
		button.setFont(new Font("TimesRoman", Font.BOLD, 13));
		
		super.add(button);

	}

	public void setButtonAction(ActionListener listener){
		button.addActionListener(listener);
	}
	
	public ArrayList<JCheckBox> getBox() {
		return box;
	}

	public ArrayList<String> getNicknames() {
		return nicknames;
	}

	public ArrayList<String> getSelectedNicknames() {
		return selectedNicknames;
	}

	public JButton getButton() {
		return button;
	}

}
