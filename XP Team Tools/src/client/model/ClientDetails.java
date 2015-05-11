package client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Client' s details as nickname and team's name. Implements
 * Comparable interface to be sorted by nickname' s alphabetic order
 * 
 * Override equals method that returns true if and only if the other
 * ClientDetails has the same nickname and the same team name
 * 
 * @author alberto
 * @see Comparable
 *
 */

public class ClientDetails implements Comparable<ClientDetails> {

	protected String nickname;
	protected String teamName;
	protected String pwd;
	private List<Integer> chatIndexList = new ArrayList<Integer>();
	
	public ClientDetails(String nickname, String teamName) {
		super();
		this.nickname = nickname;
		this.teamName = teamName;
	}
	
	public ClientDetails(String nickname, String teamName, String pwd) {
		super();
		this.nickname = nickname;
		this.teamName = teamName;
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void registerToChat(int chatIndex) {
		chatIndexList.add(chatIndex);
	}
	public List<Integer> getChatIndexList() {
		return chatIndexList;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public int compareTo(ClientDetails o) {
		return nickname.compareTo(o.getNickname());
	}
	
	public String getPwd() {
		return pwd;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ClientDetails)) {
			return false;
		}
		ClientDetails det = (ClientDetails) obj;
		if(nickname.equals(det.nickname)) {
			return true;
		}
		return false;
	}

}
