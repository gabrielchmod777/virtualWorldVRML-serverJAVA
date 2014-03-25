package ro.utcluj.learning3d.executives;

import ro.utcluj.learning3d.server.ServerCommand;

public class XmlWrappedResponse {

	public static String exception(String exception) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<response><type>"+ServerCommand.TYPE_RETURN+"</type><exception><message>"+exception+"</message></exception></response>";
		return xml;
	}
	
	public static String response(ServerCommand sender, String result, String type, String client_plugin, String client_plugin_source) {
		
		String xml = "";
		
		if("EVAL".equals(client_plugin)) {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					"<response><type>"+type+"</type>"+sender.toXmlFragment()+result+"</response>";
		} else {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					"<response><type>"+type+"</type>"+
					sender.toXmlFragment()+
					"<client_plugin>./plugins/"+client_plugin+
					"</client_plugin>"+"<client_plugin_source>"+client_plugin_source+"</client_plugin_source>"+
					"<raw>"+result+"</raw></response>";
		}
		 
		return xml;
	}

}
