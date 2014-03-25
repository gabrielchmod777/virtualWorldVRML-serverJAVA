package ro.utcluj.learning3d.parser;

import java.sql.Connection;
import java.sql.SQLException;

import org.xmlpull.v1.XmlPullParser;

public interface TagHandler {
	public void handleTag(XmlPullParser parser, String tag);
	public TagHandler getResult();
	public void saveToDatabase(Connection db) throws SQLException;
}
