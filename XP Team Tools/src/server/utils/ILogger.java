package server.utils;

import java.util.ArrayList;
import java.util.Map;

public interface ILogger {

	public void writeDatabase();
	public void setMap(Map<String, ArrayList<String>> m);

}
