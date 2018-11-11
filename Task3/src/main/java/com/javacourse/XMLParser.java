package com.javacourse;

import java.util.List;

import static com.javacourse.Constants.defaultStringTagValue;

public interface XMLParser {
    List<Page> getPageListFromXml(String pathToXmlFile);
}
