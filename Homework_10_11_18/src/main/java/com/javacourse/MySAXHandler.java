package com.javacourse;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.util.ArrayList;
import java.util.List;

import static com.javacourse.Constants.defaultStringTagValue;

public class MySAXHandler extends DefaultHandler {
    List<Page> pageEntitiesList = new ArrayList<>();

    String id = defaultStringTagValue;
    String title = defaultStringTagValue;
    String type = defaultStringTagValue;
    boolean doNeedAuthorize = false;
    boolean isFree = false;
    boolean hasEmail = false;
    boolean isDownloadable = false;
    String currentElem = defaultStringTagValue;

    public List<Page> getPageEntitiesList() {
        return pageEntitiesList;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElem = qName;
        if(qName.equals(Page.xmlNodeName))
            id = attributes.getValue("id");
        if(qName.equals("chars")){
            if(attributes.getIndex("free")!=-1)
                isFree = true;
            if(attributes.getIndex("downloadable")!=-1)
                isDownloadable = true;
            if(attributes.getIndex("hasEmail")!=-1)
                hasEmail = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (currentElem){
            case "title":
                title = new String(ch, start, length);
                break;
            case "type":
                type = new String(ch, start, length);
                break;
            case "authorise":
                doNeedAuthorize = Boolean.parseBoolean(new String(ch, start, length));
                break;
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }
}
