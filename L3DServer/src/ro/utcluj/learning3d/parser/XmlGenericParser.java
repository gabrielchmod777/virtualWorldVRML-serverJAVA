package ro.utcluj.learning3d.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlGenericParser {

	public Object parseXml(InputStream xmlDocumment, TagHandler tagHandler) throws XmlPullParserException, IOException {
		return new ParserHelper(xmlDocumment, tagHandler).parse();
	}

	public Object parseXml(String xmlDocumment, TagHandler tagHandler) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xmlDocumment.getBytes());
		return this.parseXml( is, tagHandler);
	}

	private class ParserHelper {

		private InputStream iStream;
		private TagHandler  tagHandler;

		private ParserHelper() {
			//block this
		}

		private ParserHelper(InputStream iStream, TagHandler tagHandler) {
			this.iStream = iStream;
			this.tagHandler = tagHandler;
		}

		
		public Object parse() {
			try {
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				xpp.setInput(iStream, "UTF8");
				this.skipToTag(xpp, XmlPullParser.START_TAG);
				this.processTag(xpp, xpp.getName(), this.tagHandler);
				iStream.close();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Object result = this.tagHandler.getResult();
			return result;
		}

		public void processTag(XmlPullParser parser, String tag, TagHandler tagHandler) 
				throws IOException, XmlPullParserException {
			
			tagHandler.handleTag(parser, tag);

			// this check is necessary since getNextText() pushes the current event to END_TAG!
			int nextEvent = (parser.getEventType() == XmlPullParser.END_TAG) ? 
					parser.getEventType() : parser.next();
					while (nextEvent != XmlPullParser.END_TAG) {
						if (nextEvent == XmlPullParser.START_TAG) {
							processTag(parser, parser.getName(), tagHandler); // recursive call
						}
						nextEvent = parser.next();
					}
					parser.require(XmlPullParser.END_TAG, null, tag);
		}

		public int skipToTag(XmlPullParser p, int tag) 
				throws IOException, XmlPullParserException {
			int nextEvent = p.next();
			while (nextEvent != tag) {
				nextEvent = p.next();
			}
			return nextEvent;
		}

	}

}
