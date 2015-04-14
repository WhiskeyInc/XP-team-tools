package server.model;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ConversationGenerator implements IChatStorer {

	Conversations convs;
	
	public ConversationGenerator(){
		Conversations convs = new Conversations();
	}
	
	@Override
	public void storeMessage(String rawmessage) {
		StringTokenizer tok = new StringTokenizer(rawmessage, "[]:");
		
		String team = tok.nextToken();
		String sender = tok.nextToken();
		String msg = tok.nextToken();
		
		convs.addMessage(team, sender, msg);
		
//		long time = Long.parseLong(tok.nextToken());
//		String id = tok.nextToken();
//		String sender = tok.nextToken();
//		String msg = tok.nextToken();
//		//timestamp%part1,part2,part3%sender%messaggio
		
		System.err.println(rawmessage);
		
		//convs.addConv(recipients);
	}
	
	@Override
	public ArrayList<String> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}

}
