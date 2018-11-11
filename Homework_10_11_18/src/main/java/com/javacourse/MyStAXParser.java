package com.javacourse;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.javacourse.Constants.defaultStringTagValue;
import static com.javacourse.App.logger;

public class MyStAXParser implements XMLParser {
    private List<Page> pageEntitiesList = new ArrayList<>();
    private String id = defaultStringTagValue;
    private String title = defaultStringTagValue;
    private String type = defaultStringTagValue;
    private boolean doNeedAuthorize = false;
    private boolean isFree = false;
    private boolean hasEmail = false;
    private boolean isDownloadable = false;
    private String currentElem = defaultStringTagValue;

    @Override
    public List<Page> getPageListFromXml(String pathToXmlFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try{
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(pathToXmlFile));
            while(xmlEventReader.hasNext()){
                XMLEvent currentEvent = xmlEventReader.nextEvent();
                if(currentEvent.isStartElement()){
                    StartElement startElement = currentEvent.asStartElement();
                    currentElem = startElement.getName().getLocalPart();
                    if(currentElem.equals(Page.xmlNodeName)){
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if(idAttr!=null){
                            id = idAttr.getValue();
                        }
                    }else if(currentElem.equals("title")){
                        currentEvent = xmlEventReader.nextEvent();
                        title = currentEvent.asCharacters().getData();
                    }else if(currentElem.equals("type")){
                        currentEvent = xmlEventReader.nextEvent();
                        type = currentEvent.asCharacters().getData();
                    }else if(currentElem.equals("authorize")){
                        currentEvent = xmlEventReader.nextEvent();
                        doNeedAuthorize = Boolean.parseBoolean(currentEvent.asCharacters().getData());
                    }else if(currentElem.equals("chars")){
                        Attribute charsAttr = startElement.getAttributeByName(new QName("name"));
                        if (charsAttr!=null) {
                            String charsType = charsAttr.getValue();
                            if(charsType.equals("free"))
                                isFree = true;
                            if(charsType.equals("downloadable"))
                                isDownloadable = true;
                            if(charsType.equals("hasEmail"))
                                hasEmail = true;
                        }
                    }
                }

                if(currentEvent.isEndElement()){
                    processEndElement(currentEvent.asEndElement());
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            logger.error(e.getMessage());
        }
        return pageEntitiesList;
    }

    private void processEndElement(EndElement endElement){
        currentElem = endElement.getName().getLocalPart();
        if(currentElem.equals(Page.xmlNodeName)){
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
            currentElem = defaultStringTagValue;
        }
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
