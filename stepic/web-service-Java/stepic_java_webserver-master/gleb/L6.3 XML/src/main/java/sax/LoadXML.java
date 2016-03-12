package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class LoadXML {
    public static <T> T readXML(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            //LogSAXHandler handler = new LogSAXHandler();
            SAXHandler<T> handler = new SAXHandler<T>();
            saxParser.parse(xmlFile, handler);

            return handler.getClassObj();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
