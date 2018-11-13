package com.javacourse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static com.javacourse.TestingConstants.*;

public class MyDOMParserTest {

    private MyDOMParser xmlParser;
    private static NodeList nodes;

    @BeforeClass
    public static void setUpClass() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new ConsoleErrorHandler());
        Document xmlDoc = db.parse(new InputSource(new StringReader(xml)));
        xmlDoc.getDocumentElement().normalize();
        nodes = xmlDoc.getElementsByTagName(Page.xmlNodeName);
    }

    @Before
    public void setUp() throws Exception {
        xmlParser = new MyDOMParser();
    }

    @Test
    public void getPageListFromXml_nodesAsInput_parsedCorrectly() {
        List<Page> actual = xmlParser.parseXmlNodes(nodes);
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

}