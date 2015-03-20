package model;

import java.util.ArrayList;
import java.util.HashMap;

public class USShowCase {

	private HashMap<String, String> usdescription = new HashMap<String, String>();
	private HashMap<String, Boolean> usstatus = new HashMap<String, Boolean>();
	private HashMap<String, BlackBoard> usboard = new HashMap<String, BlackBoard>();
	private ArrayList<String> uslist = new ArrayList<String>();

	public void addNewUs(String usname, String description) {
		usdescription.put(usname, description);
		usstatus.put(usname, false);
		usboard.put(usname, new BlackBoard());
		uslist.add(usname);
	}

	public String getDescription(String usname) {
		return usdescription.get(usname);
	}

	public boolean getStatus(String usname) {
		return usstatus.get(usname);
	}

	public void setDescription(String usname, String description) {
		usdescription.put(usname, description);
	}

	public void setStatus(String usname, Boolean newstatus) {
		usstatus.put(usname, newstatus);
	}

	public ArrayList<String> getUslist() {
		return uslist;
	}

	public BlackBoard getBoard(String usname) {
		return usboard.get(usname);
	}

	public void usswitch(String usname, int index2) {
		uslist.add(index2, usname);
	}

}
