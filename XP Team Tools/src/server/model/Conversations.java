package server.model;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * The class is responsible of conversations management. The
 * {@link Conversation} is stored into a LinkedList which has to be used as a
 * session buffer and it has to be saved into a different data structure as a
 * DB, file or whatever and, at a new session, it has to retrieve the last TOT
 * conversations. Those last convs have to contain TOT last messages (to be
 * implemented).
 * 
 * @author koelio, nicola
 *
 */
public class Conversations {

	private HashMap<String, Conversation> conversations = new HashMap<String, Conversation>();
	private LinkedList<Conversation> convs = new LinkedList<Conversation>();

	
	public void addMessage(String team, String author, String message){
		
		if (!(conversations.containsKey(team))){
			//TODO addConv
		}else{
			conversations.get(team).addMessage(author, message);
		}
			
	}
	
	public String getLastMessage() {// tmp method for testing
		return "Ciao";
	}

	/**
	 * Tells if there are conversations
	 * @return true if there's at least a conversation
	 */
	public boolean hasConversations() {// tmp method for testing

		return !(conversations.isEmpty());
	}

	public HashMap<String, Conversation> getConvs() {
		return conversations;
	}

	
	/**
	 * creates new conversation with separated String participant's names
	 * @param participants
	 */
	public String addConversation(String name, String... participants) {
		if (findConversation(name, participants)) {
			return "Conv esiste";
		} else {
			convs.add(new Conversation(name,participants));
			return "Conv NON esiste";
		}
	}

	private boolean findConversation(String name, String[] participants) {
			if(conversations.containsKey(name)){
				return true;
			}else
				return false;
			}			
	}
