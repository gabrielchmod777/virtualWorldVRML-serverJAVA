package ro.utcluj.learning3d.server;

import java.net.URLEncoder;

public class ServerGlobals {
	
	public static final String DB_PARAMS = "jdbc:sqlite:l3ds.db";
	public static final String VERSION_NUMBER = "0.0.1";
	public static final String SERVER_STRING  = URLEncoder.encode(
		
	   "\n******************************************************\n"
	   +"3D Learning Environment - server (v"+VERSION_NUMBER+")\n"
	   +"<@><command><params>\n"
	   +"Exit: @exit\n"
	   +"******************************************************\n");

}
