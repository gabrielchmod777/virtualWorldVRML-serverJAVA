package ro.utcluj.learning3d.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import ro.utcluj.learning3d.executives.XmlWrappedResponse;

public class Script_JS_local implements CommandTypeDependentInterpreter{

	String response;
	String library = "";
	
	@Override
	public void prepareResponse(ServerCommand execCmd, int format) {
		StringBuffer javascript = null;
        ScriptEngine runtime = null;
        ArrayList<String> params = execCmd.params;
        try {
            runtime = new ScriptEngineManager().getEngineByName("javascript");
            javascript = new StringBuffer();
            
            library = readFile("/home/gabriel/Work/3d-learning-env/implementare/L3DServer/library.js", StandardCharsets.UTF_8);
            
            System.out.println(">>>>> "+library);
            javascript.append(library);
            String program = "";
            for(String line : params) {
            	program = program + " " + line;
            	//System.out.println("JS_W> = "+line);
            }

            System.out.println("&&&&& "+program);
        	javascript.append(program);
            response = String.valueOf(runtime.eval(javascript.toString()));

            response = XmlWrappedResponse.response(execCmd, response.toString(),  ServerCommand.TYPE_RETURN, execCmd.clientPlugin, execCmd.clientPluginSource);
            
            System.out.println("Result: " + response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response = XmlWrappedResponse.exception(ex.getMessage());
            
        }
		
	}

	@Override
	public String getInterpretedResult() {
		return response;
	}
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
			}

}


//public class Script_JS_local implements CommandTypeDependentInterpreter{
//
//	String response;
//	String library = "";
//	
//	@Override
//	public void prepareResponse(ServerCommand execCmd, int format) {
//		StringBuffer javascript = null;
//        ScriptEngine runtime = null;
//        ArrayList<String> params = execCmd.params;
//        try {
//            runtime = new ScriptEngineManager().getEngineByName("javascript");
//            javascript = new StringBuffer();
//            
//            library = readFile("/home/gabriel/Work/3d-learning-env/implementare/L3DServer/library.js", StandardCharsets.UTF_8);
//            
//            System.out.println(">>>>> "+library);
//            javascript.append(library);
//            
//            for(String line : params) {
//            
//            	javascript.append(line);
//            	//System.out.println("JS> = "+line);
//            }
//
//            response = String.valueOf(runtime.eval(javascript.toString()));
//
//            response = XmlWrappedResponse.response(execCmd, response.toString(),  ServerCommand.TYPE_RETURN, execCmd.clientPlugin);
//            
//            System.out.println("Result: " + response);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//		
//	}
//
//	@Override
//	public String getInterpretedResult() {
//		return response;
//	}
//	
//	static String readFile(String path, Charset encoding) 
//			  throws IOException 
//			{
//			  byte[] encoded = Files.readAllBytes(Paths.get(path));
//			  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
//			}
//
//}
