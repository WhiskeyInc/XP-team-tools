package client.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Bilateral {@link HashMap} to collect the macroevents
 * 
 * @author nicola
 */
public class MacroEvents {
	
	HashMap<String, String> normal;
	HashMap<String, String> reverse;
	
	public MacroEvents(){
		normal = new HashMap<String, String>();
		reverse = new HashMap<String, String>();		
	}
	
	/**
	 * Adds a macro event to the collection
	 * @param id
	 * @param name
	 */
	public void addEvent(String id, String name){
		normal.put(id, name);
		reverse.put(name, id);
	}
	
	/**
	 * Gets the name of a macro event from the id
	 * @param id
	 * @return
	 */
	public String getNameFromId(String id){
		return normal.get(id);
	}
	
	/**
	 * Gets the id of a a macro event from the name
	 * @param name
	 * @return
	 */
	public String getIdFromName(String name){
		return reverse.get(name);
	}
	
	/**
	 * Empties the collection
	 */
	public void clear(){
		normal.clear();
		reverse.clear();
	}
	
	/**
	 * Gets all the list of the macro events name
	 * @return
	 */
	public ArrayList<String> getNames(){
		ArrayList<String> arr = new ArrayList<String>();
		arr.addAll(normal.values());
		return arr;
	}
	
}
