package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import ui.TeamListUI;
import ui.login.LoginUI;
import ui.login.MainLoginUI;
import client.model.ClientConnectionDetails;
import client.model.StrategyClient1_1;

/**
 * This class, with clientMain and serverMain, tests the communication between 2
 * Clients and 1 Server
 * 
 * @author alberto
 *
 */
public class MultipleChatClientMainO {
	public static void main(String[] args) {

		final MainLoginUI ui = new MainLoginUI();
		final LoginUI login = ui.getLoginUI();

		login.setLoginListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final StrategyClient1_1 client = new StrategyClient1_1(
						new ClientConnectionDetails(login.getLoginNick(),
								null, login.getPass()));

				client.openStreams("localhost", 9999);
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						try {

							client.readFromSocket();

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};

				Thread thread = new Thread(runnable);
				thread.start();
				client.sendMessageToServer(JsonMaker.teamsListRequest(client.getNickname()));
				String response = client.waitServerResponse();
				String[] teams;
				try {
					teams = JsonParser.parseTeamMembsRequest(response);
					final TeamListUI teamListUI = new TeamListUI(client, teams);
					teamListUI.setCreateListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							client.sendMessageToServer(JsonMaker.newTeamRequest(
									teamListUI.getTeamName(), client.getNickname()));
							final int index = JsonParser.parseChatIndexRequest(client
									.waitServerResponse());
							client.sendMessageToServer(JsonMaker.teamsListRequest(client.getNickname()));
							teamListUI.setIndex(index);
							teamListUI.removeTeamPanel();
							try {
								teamListUI.fillTeamPane(JsonParser.parseListOfTeamsRequest(client.waitServerResponse()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							teamListUI.refresh();
						}
					});

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

	}
}
