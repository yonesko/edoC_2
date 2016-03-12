package sax;

import com.sun.org.apache.regexp.internal.RE;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import util.ReflectionHelper;

public class SAXHandler<T> extends DefaultHandler {
    private T classObj;
    private String element;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element != null)
            ReflectionHelper.setField(classObj, element, new String(ch, start, length));
    }

    public T getClassObj() {
        return classObj;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("class")) {
            String classRef = attributes.getValue("name");
            classObj = ReflectionHelper.createInstance(classRef);
        } else
            element = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }
}
