package com.javacourse;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ConsoleErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("WARNING: Problems while parsing the XML file");
        throw new SAXException("WARNING: Problems while parsing the XML file");
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("WARNING: Problems while parsing the XML file");
        throw new SAXException("WARNING: Problems while parsing the XML file");
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("FATAL ERROR: Problems while parsing the XML file");
        throw new SAXException("FATAL ERROR: Problems while parsing the XML file");
    }
}
