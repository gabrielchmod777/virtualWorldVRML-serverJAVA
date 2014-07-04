package ro.utcluj.learning3d.server;

import java.util.ArrayList;

import ro.utcluj.learning3d.executives.XmlWrappedResponse;
import ro.utcluj.learning3d.server.CommandTypeDependentInterpreter;
import ro.utcluj.learning3d.server.ServerCommand;


public class ReturnWorldResources implements CommandTypeDependentInterpreter{

	String response;
	
	@Override
	public void prepareResponse(ServerCommand cmd, int format) {

		ArrayList<String> params = cmd.params;
		if(params!=null) {
			response = XmlWrappedResponse.response(cmd, "<archive>localhost/env.tar.gz</archive>", ServerCommand.TYPE_RETURN, cmd.clientPlugin, cmd.clientPluginSource);
		} 
	}

	@Override
	public String getInterpretedResult() {
		
		return response;
	}

}
