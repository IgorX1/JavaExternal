package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.List;

public class App
{
    public static final Logger logger;

    static {
        logger = Logger.getLogger(App.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main( String[] args )
    {
        DOMParser domParser = new DOMParser();
        List<Page> res = domParser.getPageListFromXml("xml/pages.xml");
        System.out.println(res);
    }
}
