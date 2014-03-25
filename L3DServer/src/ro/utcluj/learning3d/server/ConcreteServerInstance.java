package ro.utcluj.learning3d.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import ro.utcluj.learning3d.executives.CommandProcessor;
import ro.utcluj.learning3d.executives.CommandProcessor_step1;
import ro.utcluj.learning3d.executives.XmlWrappedResponse;
/**
 * 
 * @author gabriel
 *
 *<hr/>
 *for now - adds a dummy processing of user requests
 *<hr/>
 */
public class ConcreteServerInstance extends ServerInstance {
	
	private static ArrayList<ConcreteServerInstance> allServers = new ArrayList<ConcreteServerInstance>();
		
	/**
	 * Constructor.
	 * @param socket Network socket
	 * @param id Instance id (provide an unique id).
	 * @throws IOException
	 */
	public ConcreteServerInstance(Socket socket, Integer id) throws IOException {
		super(socket, id);
		System.out.println("ADD="+id+"\n"+this);
		allServers.add(this);
		System.out.println("ALL="+allServers.size());
	}

	/**
	 * This method will implement the procedure used to process client requests. 
	 * @param request Client's request
	 * @return Processed request (to be sent to the client).
	 */
	@Override
	public String processRequest(String request) {
		
		System.out.println("REQ..."+request);
		//System.exit(1);
		
		//dummy process request
		if("".equals(request)) {	
			return XmlWrappedResponse.exception("No command recieved.");
		}
		
		if("@exit".equals((request+"\n"))){
			allServers.remove(this);
			return "Bye..";
		}
		CommandProcessor ci = new CommandProcessor();
		String rez = ci.processCommand(request, new CommandProcessor_step1());
		return rez;
	}

	/**
	 * 
	 */
	@Override
	public void broadcastResponse(String response) {
			
			for(ConcreteServerInstance user : allServers) {
				if(user!=null) {
					user.sendResponse(response);
				}
			}
	}

}
