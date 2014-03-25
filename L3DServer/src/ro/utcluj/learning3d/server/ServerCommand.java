package ro.utcluj.learning3d.server;

import java.util.ArrayList;

public class ServerCommand {
	
	public static String TYPE_RETURN = "#ro.utcluj.learning3d.server@return";
	public static String TYPE_BROADCAST = "#ro.utcluj.learning3d.server@broadcast";
	
	public String command;
	public String type;
	public String help;
	public Integer paramCount;
	public ArrayList<String> params;
	public String[] regexp; 
	public boolean wellFormated = true;
	public String executor;
	public String clientPlugin;
	public String clientPluginSource;
	public String toXmlFragment() {
		String fragment = "<name>"+this.command+"</name>";
		return fragment;
	}
}
