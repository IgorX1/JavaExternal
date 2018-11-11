package com.javacourse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.javacourse.Constants.*;
import static com.javacourse.App.logger;

public class MyDOMParser implements XMLParser{

    private String id = defaultStringTagValue;
    private String title = defaultStringTagValue;
    private String type = defaultStringTagValue;
    private boolean doNeedAuthorize = false;
    private boolean isFree = false;
    private boolean hasEmail = false;
    private boolean isDownloadable = false;

    @Override
    public List<Page> getPageListFromXml(String pathToXmlFile) {
        List<Page> pageEntitiesList = new ArrayList<>();
        try {
            NodeList pageList = getPageNodesFromFile(pathToXmlFile);
            for(int i=0; i<pageList.getLength(); ++i){
                Node pageNode = pageList.item(i);
                if(pageNode.getNodeType()==Node.ELEMENT_NODE){
                    Element tempPage = (Element) pageNode;
                    id = tempPage.getAttribute("id");
                    NodeList pageChildren = tempPage.getChildNodes();

                    for (int a=0; a<pageChildren.getLength(); ++a) {
                        if (pageChildren.item(a).getNodeType()==Node.ELEMENT_NODE) {
                            Element tempElem = (Element)pageChildren.item(a);
                            switch (tempElem.getTagName()){
                                case "title":
                                    title = tempElem.getTextContent();
                                    break;
                                case "type":
                                    type = tempElem.getTextContent();
                                    break;
                                case "authorize":
                                    doNeedAuthorize = Boolean.parseBoolean(tempElem.getTextContent());
                                    break;
                                case "chars":
                                    switch (tempElem.getAttribute("name")){
                                        case "free":
                                            isFree = true;
                                            break;
                                        case "hasEmail":
                                            hasEmail = true;
                                            break;
                                        case "downloadable":
                                            isDownloadable = true;
                                            break;
                                    }
                                    break;
                            }

                        }
                    }

                    pageEntitiesList.add(new Page.PageBuilder(id)
                            .title(title)
                            .type(type)
                            .isDownloadable(isDownloadable)
                            .doNeedAuthorize(doNeedAuthorize)
                            .hasEmail(hasEmail)
                            .isFree(isFree)
                            .build()
                    );

                    resumeDefaultContainerVariableValues();

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Technical error occurred while parsing your file");
            logger.error(e.getMessage());
        }

        return pageEntitiesList;
    }

    private NodeList getPageNodesFromFile(String pathToXmlFile) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new ConsoleErrorHandler());
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema s = sf.newSchema(new File(schemaPath));
        dbf.setValidating(false);
        dbf.setSchema(s);
        Document xmlDoc = db.parse(new File(pathToXmlFile));
        xmlDoc.getDocumentElement().normalize();
        return xmlDoc.getElementsByTagName(Page.xmlNodeName);
    }


    private void resumeDefaultContainerVariableValues(){
        id = defaultStringTagValue;
        title = defaultStringTagValue;
        type = defaultStringTagValue;
        doNeedAuthorize = false;
        isFree = false;
        hasEmail = false;
        isDownloadable = false;
    }
}
