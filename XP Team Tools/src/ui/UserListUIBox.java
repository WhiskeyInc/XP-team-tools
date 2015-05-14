package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class UserListUIBox extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<JCheckBox> box = new ArrayList<JCheckBox>();
	private String[] nicknames;
	private JPanel nicksPanel = new JPanel();
	private JButton button = new JButton("Start new chat");
	private ArrayList<String> selectedNicknames = new ArrayList<String>();

	public UserListUIBox() {
		super.setBackground(new Color(244, 249, 228));
		super.setBackground(new Color(248, 244, 255));
		super.setLayout(new GridBagLayout());
		super.setPreferredSize(new Dimension(250, 400));
		super.setMinimumSize(new Dimension(250, 400));
		super.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(null, "Team Members panel",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new java.awt.Font(
								"Verdana", 1, 8)), BorderFactory
				.createEmptyBorder(1, 1, 1, 1)));
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		JLabel label = new JLabel("Team members");
		label.setFont(new Font("TimesRoman", Font.BOLD, 16));
		lim.insets = new Insets(10, 5, 5, 5);
		super.add(label, lim);

	}

	public UserListUIBox(String[] nicknames) {
		super();
		this.nicknames = nicknames;
	}

	public void setButtonAction(ActionListener listener) {
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

	// TODO javadoc
	public void setNicknames(String[] nicknames) {
		this.nicknames = nicknames;
		int size = nicknames.length;
		nicksPanel.removeAll();
		nicksPanel.setLayout(new GridLayout(size, 1));
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
			nicksPanel.add(box.get(i));
		}
		JScrollPane pane = new JScrollPane(nicksPanel);
		// pane.setPreferredSize(new Dimension(170, 300));
		// pane.setMinimumSize(new Dimension(170, 300));
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(5, 5, 5, 2);
		lim.weighty = 1f;
		super.add(pane, lim);
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 2;
		lim.insets = new Insets(5, 2, 5, 5);
		lim.weighty = 1f;
		button.setFont(new Font("TimesRoman", Font.BOLD, 10));
		button.setPreferredSize(new Dimension(170, 40));
		button.setMinimumSize(new Dimension(170, 40));
		super.add(button, lim);
	}

	public JButton getButton() {
		return button;
	}
	
	public void deselectAll() {
		for (JCheckBox jCheckBox : box) {
			jCheckBox.setSelected(false);
			jCheckBox.setForeground(Color.BLACK);
		}
	}

}