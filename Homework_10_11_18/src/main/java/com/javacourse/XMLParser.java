package com.javacourse;

import java.util.List;

public interface XMLParser {
    List<Page> getPageListFromXml(String pathToXmlFile);
}
