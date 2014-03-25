package ro.utcluj.learning3d.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.xmlpull.v1.XmlPullParserException;

import ro.utcluj.learning3d.parser.CommandsXmlHandler;
import ro.utcluj.learning3d.parser.XmlGenericParser;
/**
 * 
 * @author gabriel
 *
 *<hr/>
 *Starts the server and waits for connections on port 8080.
 *Every new connection receives a instance server.
 *Closing a connection and is done by sending a "@exit" command.
 *<hr/>
 */
public class L3DServerManager {
	
	public static StaticDB database;
	
	public static void main(String[] args){
		
		database = new StaticDB();
		/*
		 *   incarca comenzile
		 */
		XmlGenericParser parser = new XmlGenericParser();
		CommandsXmlHandler handler = new CommandsXmlHandler();
		File file = new File("./commands.xml");
		FileInputStream xmlFile = null;
		try {
			xmlFile = new FileInputStream(file);
			parser.parseXml(xmlFile, handler);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (xmlFile != null)
					xmlFile.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		/*
		 *  in asteptarea clientilor
		 */
		int serverId=1;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(8080);
			for(;;){
				Socket s = serverSocket.accept();
				System.out.print("\nNew server [id="+serverId+"]");
				/*
				 *  client nou
				 */
				Thread t = new ConcreteServerInstance(s, serverId);
				serverId++;
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


