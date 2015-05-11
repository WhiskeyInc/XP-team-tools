package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import protocol.JsonMaker;
import protocol.JsonParser;
import string.formatter.Formatter;
import tests.MultipleChatClientMainO;
import timer.TimerFormatter;
import client.model.ClientDetails;
import client.model.IClientService;
import client.model.IListService;
import client.model.SessionManager;
import client.model.SetListOfTeamsService;
import client.model.SetMembsService;
import client.model.SetMessageService;
import client.model.SetNewChatService;
import client.model.SetTimeStampService;
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
	private JTextField teamName;
	private int index;

	private static final long serialVersionUID = 1L;

	public TeamListUI(StrategyClient1_1 client, String[] teams) {
		this.client = client;
		mainPanel = new JPanel(new GridBagLayout());
		addLoggedLabel();
		addLogoutBtn();
		fillTeamPane(teams);
		addNewTeamPanel();
		super.getContentPane().add(mainPanel);
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
		super.getContentPane().add(mainPanel);
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
		newTeamPanel.setBorder((BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(null, "Create new team",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new java.awt.Font(
								"Verdana", 1, 8)), BorderFactory
						.createEmptyBorder(1, 1, 1, 1))));
		newTeamPanel.setLayout(new GridBagLayout());

		teamName = new JTextField();
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
			teamLabel.addMouseListener(new TeamLabelMouseAdapter(teamLabel,
					original) {

				@Override
				public void setCursorOut() {
					Cursor cursor = Cursor.getDefaultCursor();
					cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
					setCursor(cursor);
				}

				@Override
				public void setCursorIn() {
					Cursor cursor = Cursor.getDefaultCursor();
					cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					setCursor(cursor);
				}

				@Override
				public void launch() {
					final IClientService[] services = new IClientService[3];
					services[0] = new SetMessageService();
					services[1] = new SetTimeStampService();
					services[2] = new SetNewChatService();
					final IListService serviceTeamMembs = new SetMembsService();
					final IListService teamsServices = new SetListOfTeamsService();
					client.getClientDetails().setTeamName(teamLabel.getText());
					client.addService(JsonParser.CHAT, services[0]);
					client.addService(JsonParser.TIMER, services[1]);
					client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX),
							services[2]);
					client.addListService(
							Integer.parseInt(JsonMaker.TEAM_MEMBS),
							serviceTeamMembs);
					client.addListService(Integer.parseInt(JsonMaker.TEAMS),
							teamsServices);

					// client.addService(Integer.parseInt(JsonMaker.CHAT_INDEX),
					// chatIndexService);
					// client.addService(Integer.parseInt(JsonMaker.CONFIRM),
					// confirmService);
					//
//TODO
					
					System.err.println("L' indice della chat è : " + index
							+ " [" + StrategyClient1_1.class + "]");
					
					//client.waitServerResponse();
					final String indexString = String.valueOf(index);
					client.sendMessageToServer(JsonMaker.chatRequest(
							"- " + client.getNickname() + " has created "
									+ client.getTeamName() + " -", indexString));
					if (client.getNickname().equals("Alb")) {
						client.sendMessageToServer(JsonMaker
								.addTeamMembRequest(client.getClientDetails()));
						client.sendMessageToServer(JsonMaker.addTeamMembRequest(new ClientDetails("Pav", "Prova")));
						client.sendMessageToServer(JsonMaker.addTeamMembRequest(new ClientDetails("Nic", "Prova")));
					}
					
					Runnable runnable2 = new Runnable() {

						@Override
						public void run() {
							MainUIObserver ui = new MainUIObserver(services,
									serviceTeamMembs, client, index);

							System.err.println(EventQueue.isDispatchThread()
									+ " " + MultipleChatClientMainO.class);
							final ChatUIObserverStrategy1 chatUI = ui
									.getChatUI();
							final TimerUIObserverStrategy timerUI = ui
									.getTimerUI();
							final UserListUI listUI = ui.getUserListUI();
							final String teamName = client.getTeamName();
							ui.setChatUI(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									client.sendMessageToServer(JsonMaker
											.chatRequest(teamName,
													client.getNickname()));
									chatUI.emptyMessageArea();
								}
							});
							final String formattedNickname = Formatter
									.formatNickname(client.getNickname());

							ui.setTimerUI(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									if (timerUI.isTimeStampValid(timerUI
											.getTimeStamp())) {
										int[] time = TimerFormatter
												.getMinSec(timerUI
														.getTimeStamp());
										timerUI.setTimerEditable(false);// TODO
																		// se è
																		// connesso...
										client.sendMessageToServer(JsonMaker
												.timerRequest(indexString,
														time[0], time[1]));
									}
								}
							});

							ui.setUserListUi(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									SessionManager sessionManager = SessionManager
											.getInstance();
									if (sessionManager.hasChat(index)) {
										if (!sessionManager.isChatOpen(index)) {
											NewChatWorker newChatWorker = new NewChatWorker(
													listUI, client, services);
											newChatWorker.execute();
										} else {
											listUI.deselectAll();
										}
									} else {
										sessionManager
												.registerChatOpening(index);
										NewChatWorker newChatWorker = new NewChatWorker(
												listUI, client, services);
										newChatWorker.execute();
									}

								}
							});

							chatUI.setEnterListener(new KeyListener() {
								@Override
								public void keyTyped(KeyEvent e) {
								}

								@Override
								public void keyReleased(KeyEvent e) {
								}

								@Override
								public void keyPressed(KeyEvent e) {

									if (e.getKeyCode() == KeyEvent.VK_ENTER) {
										e.consume();
										client.sendMessageToServer(JsonMaker.chatRequest(

												formattedNickname
														+ chatUI.getMessage(),
												"" + index));
										chatUI.emptyMessageArea();
										// chat.getMessageArea().setCaretPosition(0);
									}
								}
							});

							ui.setMeetingButtonAction(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {

									final MeetingUIDetails ask = new MeetingUIDetails();
									EventCreationController eventContr = new EventCreationController(
											ask, client);
									ask.setCreateButtonListener(eventContr);
								}
							});
						}
					};
					SwingUtilities.invokeLater(runnable2);
					//TODO
					
				}
			});
			panel.add(teamLabel, lim);

		}
		teamPane = new JScrollPane(panel);
		addTeamPanel();
	}

	public void setCreateListener(ActionListener createListener) {
		createTeam.addActionListener(createListener);
	}

	public void removeTeamPanel() {
		super.getContentPane().remove(mainPanel);
		mainPanel.remove(teamPane);
	}

	public void refresh() {

		// super.getContentPane().invalidate();
		super.getContentPane().revalidate();
		// super.getContentPane().repaint();
		super.revalidate();
		// super.repaint();
	}

	public String getTeamName() {
		// TODO, checkare la validità
		return teamName.getText();
	}

	@Override
	public void setCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		super.setCursor(cursor);
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
