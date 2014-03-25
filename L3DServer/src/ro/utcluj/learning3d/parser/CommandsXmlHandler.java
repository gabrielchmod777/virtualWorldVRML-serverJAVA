package ro.utcluj.learning3d.parser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import ro.utcluj.learning3d.server.ServerCommand;

public class CommandsXmlHandler implements TagHandler{

	public ServerCommand cmd = null;
	public static HashMap<String, ServerCommand> mapOfCommands = null;

	@Override
	public void handleTag(XmlPullParser parser, String tag) {
		if(mapOfCommands == null) {
			mapOfCommands = new HashMap<String, ServerCommand>();
		}
		if(cmd == null) {
			cmd = new ServerCommand();
		} else {
			try {
				if("name".equals(tag)) {
					this.cmd.command = parser.nextText();
				} else if ("type".equals(tag)) {
					this.cmd.type = parser.nextText();
				} else if ("help".equals(tag)) {
					this.cmd.help = parser.nextText();
				} else if ("param_count".equals(tag)) {
					this.cmd.paramCount = Integer.parseInt(parser.nextText());
				} else if("client_plugin".equals(tag)) {
					this.cmd.clientPlugin = parser.nextText();
				} else if("client_plugin_source".equals(tag)) {
					this.cmd.clientPluginSource = parser.nextText();
				} else if ("param_regexp".equals(tag)) {
					if(this.cmd.paramCount>0) {
						this.cmd.regexp = new String[this.cmd.paramCount];
						int index = 0;
						StringTokenizer t = new StringTokenizer(parser.nextText());
						while(t.hasMoreTokens()) {
							try {
								this.cmd.regexp[index] = t.nextToken();
							} catch (IndexOutOfBoundsException e) {
								e.printStackTrace();
								this.cmd.wellFormated = false;
							}
						}
					} else {
						this.cmd.regexp = null;
					}
				} else if("executor".equals(tag)) {
					this.cmd.executor = parser.nextText();
					mapOfCommands.put(this.cmd.command, cmd);
					cmd = new ServerCommand();
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				//assume limit-less number of parameters
				//and all numbers
				//.. and command becomes badly formatted
				this.cmd.paramCount = -1;
				this.cmd.regexp[0] = "0";
				this.cmd.wellFormated = false;

			}
		}

	}

	@Override
	public TagHandler getResult() {
		return this;
	}

	@Override
	public void saveToDatabase(Connection db) throws SQLException {
		// not needed now
	}

}
