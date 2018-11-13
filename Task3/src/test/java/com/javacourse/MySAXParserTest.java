package com.javacourse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.javacourse.App.logger;
import static com.javacourse.Constants.schemaPath;
import static com.javacourse.TestingConstants.xml;
import static com.javacourse.TestingConstants.xmlWrong;
import static org.junit.Assert.*;

public class MySAXParserTest {

    @Test
    public void parseTest() throws IOException, SAXException, ParserConfigurationException {
        List<Page> actual = getParserPages();
        List<Page> expected = new ArrayList<>(Arrays.asList(new Page.PageBuilder("ID-1")
                        .title("Home page")
                        .type("ads")
                        .doNeedAuthorize(false)
                        .isFree(true)
                        .build(),
                new Page.PageBuilder("ID-2")
                        .title("World news")
                        .type("news")
                        .isFree(true)
                        .hasEmail(true)
                        .build(),
                new Page.PageBuilder("ID-3")
                        .title("Mirror web page")
                        .type("mirror")
                        .doNeedAuthorize(true)
                        .isDownloadable(true)
                        .hasEmail(true)
                        .build()
        ));

        assertEquals(actual, expected);
    }

    List<Page> getParserPages() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        MySAXHandler handler = new MySAXHandler();
        SAXParser parser = spf.newSAXParser();
        parser.parse(new InputSource(new StringReader(xml)), handler);
        return handler.getPageEntitiesList();
    }

}