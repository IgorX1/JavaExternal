package com.javacourse;

import java.util.List;

public interface XMLParser {
    List<Page> getPageCollectionFromXml(String pathToXmlFile);
}
