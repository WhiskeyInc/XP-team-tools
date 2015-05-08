package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import client.model.StrategyClient1_1;

public class TeamListUI extends JFrame {

	/**
	 * 
	 */
	private StrategyClient1_1 client;
	private JButton logout = new JButton("Logout");
	private JButton createTeam = new JButton("Create");
	private JScrollPane teamPane;
	private JPanel mainPanel;

	private static final long serialVersionUID = 1L;

	public TeamListUI(StrategyClient1_1 client, String[] teams) {
		this.client = client;
		mainPanel = new JPanel(new GridBagLayout());
		addLoggedLabel();
		addLogoutBtn();
		fillTeamPane(teams);

		addTeamPanel();
		addNewTeamPanel();
		super.add(mainPanel);
		pack();
		setVisible(true);
	}

	private void addLoggedLabel() {
		JLabel loggedLabel = new JLabel("Logged as " + client.getNickname());
		loggedLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
		loggedLabel.setForeground(Color.RED);
		GridBagConstraints lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(40, 10, 10, 15);
		mainPanel.add(loggedLabel, lim);
	}

	private void addLogoutBtn() {
		GridBagConstraints lim;
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(5, 5, 5, 5);
		mainPanel.add(logout, lim);
	}

	private void addTeamPanel() {
		GridBagConstraints lim;
		lim = new GridBagConstraints();
		lim.gridx = 1;
		lim.gridy = 0;
		lim.gridheight = 2;
		lim.insets = new Insets(20, 20, 20, 20);
		
		teamPane.setPreferredSize(new Dimension(200, 300));
		teamPane.setMinimumSize(new Dimension(200, 300));
		mainPanel.add(teamPane, lim);
	}

	private void addNewTeamPanel() {
		GridBagConstraints lim;
		JPanel newTeamPanel = createNewTeamPanel();
		lim = new GridBagConstraints();
		lim.gridx = 2;
		lim.gridy = 0;
		lim.gridheight = 2;
		lim.insets = new Insets(10, 15, 10, 15);
		newTeamPanel.setPreferredSize(new Dimension(120, 100));
		newTeamPanel.setMinimumSize(new Dimension(120, 100));
		mainPanel.add(newTeamPanel, lim);
	}

	private JPanel createNewTeamPanel() {
		GridBagConstraints lim;
		JPanel newTeamPanel = new JPanel();
		newTeamPanel.setBorder((BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(null, "Create new team",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new java.awt.Font(
								"Verdana", 1, 8)), BorderFactory
				.createEmptyBorder(1, 1, 1, 1))));
		newTeamPanel.setLayout(new GridBagLayout());

		JTextField teamName = new JTextField();
		teamName.setPreferredSize(new Dimension(100, 20));
		teamName.setMinimumSize(new Dimension(100, 20));
		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 0;
		lim.insets = new Insets(5, 5, 5, 5);
		newTeamPanel.add(teamName, lim);

		lim = new GridBagConstraints();
		lim.gridx = 0;
		lim.gridy = 1;
		lim.insets = new Insets(5, 5, 5, 5);
		newTeamPanel.add(createTeam, lim);
		return newTeamPanel;
	}

	public void setLogoutListener(ActionListener logoutListener) {
		logout.addActionListener(logoutListener);
	}

	public void fillTeamPane(String[] teams) {
//		super.getContentPane().remove(mainPanel);
//		mainPanel.remove(teamPane);
//		teamPane.removeAll();
		JPanel panel = new JPanel();
		panel.setBorder((BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(null, "Teams Panel",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new java.awt.Font(
								"Verdana", 1, 8)), BorderFactory
				.createEmptyBorder(1, 1, 1, 1))));
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(new Dimension(200, 300));
		panel.setMinimumSize(new Dimension(200, 300));
		for (int i = 0; i < teams.length; i++) {
			GridBagConstraints lim = new GridBagConstraints();
			lim.gridx = 0;
			lim.gridy = i;
			lim.insets = new Insets(5, 5, 0, 5);
			final JLabel teamLabel = new JLabel(teams[i]);
			final Font original = new Font("TeamsRoman", Font.BOLD, 16);
			teamLabel.setFont(original);
			teamLabel.addMouseListener(new MouseAdapter() {
			 

				@Override
				public void mouseExited(MouseEvent e) {
					Cursor cursor = Cursor.getDefaultCursor();
					cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
					setCursor(cursor);
					teamLabel.setFont(original);

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Cursor cursor = Cursor.getDefaultCursor();
					cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					setCursor(cursor);
					Font font = teamLabel.getFont();
					Map attributes = font.getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					teamLabel.setFont(font.deriveFont(attributes));

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO
				}
			});
			panel.add(teamLabel, lim);
		
		}
		
		teamPane = new JScrollPane(panel);
//		addTeamPanel();
//		super.getContentPane().add(mainPanel);
//		refresh();
//		pack();
	}

	public void setCreateListener(ActionListener createListener) {
		createTeam.addActionListener(createListener);
	}

	public void refresh() {
		// super.getContentPane().invalidate();
		super.getContentPane().revalidate();
		// super.getContentPane().repaint();
		super.revalidate();
		// super.repaint();
	}
}
