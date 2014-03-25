package ro.utcluj.learning3d.executives;

import ro.utcluj.learning3d.server.ServerCommand;
import ro.utcluj.learning3d.server.ServerCommandExecutor;

public class CommandProcessor{

	
	public String processCommand(String command, CommandProcessor_step1 processor ) {
		processor.process(command);
		if(processor.hasValidForm()){
			try{
				ServerCommand execCmd = processor.getCommandInExecutableForm();
				String type = execCmd.type;
				String response = ServerCommandExecutor.execute(type, execCmd);
				return response;
				
			} catch (NullPointerException e) {
				return XmlWrappedResponse.exception("Command is invalid OR not well formated [NULL]");
			}
		} else {
			return XmlWrappedResponse.exception("Command is invalid OR not well formated ["+command+"]");
		}
	}
}


