package ro.utcluj.learning3d.server;

import java.util.ArrayList;

import ro.utcluj.learning3d.executives.XmlWrappedResponse;


public class TEST_COMMAND_Math_Adition implements CommandTypeDependentInterpreter {

	String response;

	@Override
	public String getInterpretedResult() {
		return response;
	}

	@Override
	public void prepareResponse(ServerCommand cmd, int format) {
		Double rez = 0.0;
		ArrayList<String> params = cmd.params;
		if(params!=null) {
			for(String param : params) {
				if(isNumber(param)){
					rez=rez+Double.parseDouble(param);
				}
			}
			response = XmlWrappedResponse.response(cmd, rez.toString(), ServerCommand.TYPE_RETURN, cmd.clientPlugin, cmd.clientPluginSource);
		} else { 
			response = XmlWrappedResponse.response(cmd, "0", ServerCommand.TYPE_RETURN, cmd.clientPlugin, cmd.clientPluginSource);
		}
	}

	private boolean isNumber(String s){
		try{
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
