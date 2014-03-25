package ro.utcluj.learning3d.server;

import java.util.ArrayList;

import ro.utcluj.learning3d.executives.XmlWrappedResponse;

public class SQL_GET implements CommandTypeDependentInterpreter{

	String response;
	
	@Override
	public void prepareResponse(ServerCommand cmd, int format) {
			response = "";
			
			ArrayList<String> params = cmd.params;
			if(params!=null) {
				String sql = "";
				for(String param : params) {
					sql = sql+" "+param;
					
				}
				response = StaticDB.query(sql);
				response = XmlWrappedResponse.response(cmd, response, ServerCommand.TYPE_RETURN, cmd.clientPlugin, cmd.clientPluginSource);
			} else { 
				response = XmlWrappedResponse.response(cmd, "ERROR", ServerCommand.TYPE_RETURN, cmd.clientPlugin, cmd.clientPluginSource);
			}
	}

	@Override
	public String getInterpretedResult() {
		
		return response;
	}

}
