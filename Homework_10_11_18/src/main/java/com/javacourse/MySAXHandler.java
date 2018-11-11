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

    boolean isSetAuthorize = false;
    boolean isSetTitle = false;
    boolean isSetType = false;

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
        else if(qName.equals("chars")){
            if(attributes.getIndex("free")!=-1)
                isFree = true;
            if(attributes.getIndex("downloadable")!=-1)
                isDownloadable = true;
            if(attributes.getIndex("hasEmail")!=-1)
                hasEmail = true;
        }else if(qName.equals("title"))
            isSetTitle = true;
        else if(qName.equals("type"))
            isSetType = true;
        else if(qName.equals("authorize"))
            isSetAuthorize = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElem = qName;
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
                if(isSetTitle){
                    title = new String(ch, start, length);
                    isSetTitle = false;
                }
                break;
            case "type":
                if(isSetType) {
                    type = new String(ch, start, length);
                    isSetType = false;
                }
                break;
            case "authorise":
                if(isSetAuthorize){
                    doNeedAuthorize = Boolean.parseBoolean(new String(ch, start, length));
                    isSetAuthorize = false;
                }
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
