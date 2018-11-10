package com.javacourse;

import java.util.List;

import static com.javacourse.Constants.defaultStringTagValue;

public abstract class XMLParser {

    String id = defaultStringTagValue;
    String title = defaultStringTagValue;
    String type = defaultStringTagValue;
    boolean doNeedAuthorize = false;
    boolean isFree = false;
    boolean hasEmail = false;
    boolean isDownloadable = false;

    abstract List<Page> getPageListFromXml(String pathToXmlFile);
}
