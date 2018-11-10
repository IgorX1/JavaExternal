package com.javacourse;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static com.javacourse.Constants.*;
import static com.javacourse.App.logger;

public class MySAXParser extends DefaultHandler implements XMLParser {

    @Override
    public List<Page> getPageListFromXml(String pathToXmlFile) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        MySAXHandler handler = new MySAXHandler();
        List<Page> pageEntitiesList = null;
        try {
            SAXParser parser = spf.newSAXParser();
            parser.parse(new File(pathToXmlFile), handler);
            pageEntitiesList = handler.getPageEntitiesList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e.getMessage());
        }
        return pageEntitiesList;
    }

}
