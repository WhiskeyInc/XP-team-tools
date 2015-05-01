package ui;

import java.awt.Color;
import java.awt.Font;
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
	private String[] nicknames;
	private JButton button = new JButton("Start chat");
	private ArrayList<String> selectedNicknames = new ArrayList<String>();

	public UserListUI() {

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
		super.add(pane);
		
		button.setFont(new Font("TimesRoman", Font.BOLD, 13));
		
		super.add(button);
	}
	
	public JButton getButton() {
		return button;
	}

}
