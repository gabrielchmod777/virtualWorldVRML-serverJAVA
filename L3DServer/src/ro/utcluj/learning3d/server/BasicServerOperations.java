package ro.utcluj.learning3d.server;
/**
 * 
 * @author gabriel
 *
 */
public interface BasicServerOperations {
	/**
	 * This method will implement the procedure used to process client requests. 
	 * @param request Client's request
	 * @return Processed request (to be sent to the client).
	 */
	public String processRequest(String request);
	/**
	 * Sends a response to the client. (ServerInstance abstract class uses this
	 * method to send the data returned by @see processRequest )
	 * @param response Response to be sent to the client.
	 */
	
	public void sendResponse(String response);
	/**
	 * Broadcast a response to all clients. (ServerInstance abstract class uses this
	 * method to send the data returned by @see processRequest )
	 * @param response Response to be sent to the client.
	 */
	public void broadcastResponse(String response);
	/**
	 * @return a string containing the server description and version number 
	 * ( both pieces of information are defined in ServerGlobals )
	 */
	public String serverDescription();
}
