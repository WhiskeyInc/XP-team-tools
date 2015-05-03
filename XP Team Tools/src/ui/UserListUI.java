package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UserListUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<JCheckBox> box = new ArrayList<JCheckBox>();
	private String[] nicknames;
	private JButton button = new JButton("Start chat");
	private ArrayList<String> selectedNicknames = new ArrayList<String>();

	public UserListUI() {
		super.setBackground(new Color(228, 243, 243));
		super.setLayout(new GridBagLayout());
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		JLabel label = new JLabel("Team members");
		label.setFont(new Font("TimesRoman", Font.BOLD, 20));
		lim.insets = new Insets(5, 20, 5, 5);
		super.add(label, lim);

	}
	
	

	public UserListUI(String[] nicknames) {
		super();
		this.nicknames = nicknames;
	}



	public void setButtonAction(ActionListener listener){
		button.addActionListener(listener);
	}
	
	public ArrayList<JCheckBox> getBox() {
		return box;
	}

	public String[] getNicknames() {
		return nicknames;
	}

	public ArrayList<String> getListOfSelectedNicknames() {
		return selectedNicknames;
	}
	
	public String[] getSelectedNicknames() {
		int size = selectedNicknames.size();
		String[] sel = new String[size];
		for (int i = 0; i < size; i++) {
			sel[i] = selectedNicknames.get(i);
		}
		return sel;
	}
	//TODO javadoc
	public void setNicknames(String[] nicknames) {
		this.nicknames = nicknames;
		int size = nicknames.length;
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(size,1));
		super.removeAll();
		//Spostare su un altro pannello...
		super.setLayout(new GridBagLayout());
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		JLabel label = new JLabel("Team members");
		label.setFont(new Font("TimesRoman", Font.BOLD, 20));
		lim.insets = new Insets(10, 10, 10, 10);
		super.add(label, lim);
		// fino a qua
		box.clear();
		for (int i = 0; i < size; i++) {
			box.add(new JCheckBox(nicknames[i]));
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
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(5, 5, 5, 5);
		super.add(pane, lim);
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 1;
		lim.insets = new Insets(5, 5, 5, 5);
		button.setFont(new Font("TimesRoman", Font.BOLD, 13));
		
		super.add(button, lim);
	}
	
	public JButton getButton() {
		return button;
	}

}
