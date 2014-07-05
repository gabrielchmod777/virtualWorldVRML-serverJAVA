package ro.utcluj.learning3d.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URLEncoder;
/**
 * 
 * @author gabriel
 * 
 * <hr/>
 * Implements the functionality of a server instance. A server instance
 * runs on a separate thread and is responsible for reading the requests 
 * of a single user. The instance also sends back the response.
 * The only thing that remains to be implemented in the concrete server instance
 * is the request processing function.
 * <hr/>
 */
public abstract class ServerInstance extends Thread
implements BasicServerOperations{

	private Socket 			socket;
	private Integer 		serverId;
	private BufferedReader	istream;
	private PrintStream 	ostream;
	private boolean 		keepRunning;

	@SuppressWarnings("unused")
	private ServerInstance() {
		// blocked
	}

	/**
	 * This is the only available constructor. The default one is made private.
	 * @param socket The network socket returned by ServerSocket's accept() method.
	 * @param id instance's ID (provide an unique ID)
	 * @throws IOException
	 */
	public ServerInstance(Socket socket, Integer id) throws IOException {
		this.socket = socket;
		this.serverId = id;

		this.istream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.ostream = new PrintStream(socket.getOutputStream());
		this.keepRunning = true;
	}

	/**
	 * Sends a response to the client. (ServerInstance abstract class uses this
	 * method to send the data returned by processRequest )
	 * @param response Response to be sent to the client.
	 */
	@Override
	public void sendResponse(String response) {
		System.out.println("RESP="+response);
		this.ostream.println(URLEncoder.encode(response));
		//this.ostream.println(response);
	}

	/**
	 * @return a string containing the server description and version number 
	 * ( both pieces of information are defined in ServerGlobals )
	 */
	@Override
	public String serverDescription() {
		return ServerGlobals.SERVER_STRING;
	}

	/**
	 * Calls or executes operations in the following order:
	 * At creation: sends the server description to the client.
	 * In the loop: getRequest / processRequest 
	 * (to be implemented in the concrete class) / send Request
	 */
	@Override
	public void run() {
		super.run();

		this.ostream.println(this.serverDescription().toCharArray());

		while(keepRunning) {
			String request = "";
			try {
				request = this.getRequest();
			} catch (IOException e) {
				e.printStackTrace();
				request = "";
			}

			try{

				String response = this.processRequest(request);
				if(response.contains(ServerCommand.TYPE_BROADCAST)) {
					this.broadcastResponse(response);
				} else {
					this.sendResponse(response);
				}
			} catch (Exception e){
				e.printStackTrace();
				this.sendResponse("NULL_RESPONSE");
			}
		}

		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the response from the client. Also sets the
	 * keepRunnig flag to false if the client sends the
	 * "@exit" command
	 * @return The request from client.
	 * @throws IOException
	 */
	private String getRequest() throws IOException {
		String line = "";

		try{
			//this.istream.readLine();
			int c = 0;
			while(((char)c)!='\n'){
				c= this.istream.read();
				//System.out.println((char)c);
				line = line+(char)c;
				//System.out.println(line);
				if(c==-1){
					this.keepRunning = false;
					break;
				}
			}
			if(line.equals(("@exit"+"\n"))){
				this.keepRunning = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.keepRunning = false;
		}
		
		System.out.println("COMANDA :: "+line);
		return line;
	}

}

