package client.model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * The team is composed by its name and by a set of ClientDetails that
 * guarantees the uniqueness within the team.
 * The set is a concrete TreeSet that sorts the attendants by their nickname
 * 
 * @author alberto
 * @see TreeSet
 * @see ClientDetails
 *
 */
public class Team {

	private String name;
	private Set<String> membersSet = new TreeSet<String>();
	private int chatIndex;

	public Team(String name) {
		this.name = name;
	}

	/**
	 * Get the team name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Add attendant to the team' s set
	 * @param clientDetails
	 */

	public void addMember(String memberNick) {
		membersSet.add(memberNick);
	}
	/**
	 * Returns the attendant's set
	 * @return
	 */
	public String[] getMembers() {
		String[] membs = new String[membersSet.size()];
		Iterator<String> iter = membersSet.iterator();
		int i = 0;
		while(iter.hasNext()){
			membs[i] = iter.next();
			i++;
		}
		return membs;
	}

	public void remove(String memberNick) {
		membersSet.remove(memberNick);
	}
	
	public boolean hasMember(String memberNick) {
		Iterator<String> iter = membersSet.iterator();
		while(iter.hasNext()) {
			if(iter.next().equals(memberNick)) {
				return true;
			}
		}
		return false;
	}
	
	public int getChatIndex() {
		return chatIndex;
	}
	public void setChatIndex(int chatIndex) {
		this.chatIndex = chatIndex;
	}

}
