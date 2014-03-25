package ro.utcluj.learning3d.executives;

import java.util.ArrayList;
import java.util.StringTokenizer;

import ro.utcluj.learning3d.parser.CommandsXmlHandler;
import ro.utcluj.learning3d.server.ServerCommand;


public class CommandProcessor_step1{
	//TODO : verifica parametri
	//private String[] regexp;
	private boolean validForm = true;
	ServerCommand cmd;


	public void process(String command) {
		try {
			StringTokenizer t = new StringTokenizer(command);
			while(t.hasMoreTokens()) {
				String s = t.nextToken();
				if(s.contains("@")){
					cmd = CommandsXmlHandler.mapOfCommands.get(s);
					if(cmd==null) {
						this.validForm = false;
						break;
					} else {
						cmd.params = new ArrayList<String>();
					}
				} else {
					cmd.params.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean hasValidForm() {
		return this.validForm;
	}

	public ServerCommand getCommandInExecutableForm() {
		return this.cmd;
	}

}
