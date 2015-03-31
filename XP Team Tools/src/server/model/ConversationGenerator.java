package server.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ConversationGenerator implements IChatStorer {

	private long time;
	private String sender;
	private String msg;
	Conversations convs;
	
	public ConversationGenerator(){
		Conversations convs = new Conversations();
	}
	
	@Override
	public void storeMessage(String rawmessage) {
		StringTokenizer tok = new StringTokenizer(rawmessage, "%");
		long time = Long.parseLong(tok.nextToken());
		String[] recipients = tok.nextToken().split(",");
		String sender = tok.nextToken();
		String msg = tok.nextToken();
		//timestamp%part1,part2,part3%sender%messaggio
		convs.addConv(recipients);
	}
	
	@Override
	public ArrayList<String> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}

}
