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
        MyDOMParser myDomParser = new MyDOMParser();
        List<Page> res = myDomParser.getPageListFromXml("xml/pages.xml");
        System.out.println(res);

        MySAXParser mySaxParser = new MySAXParser();
        List<Page> res2 = mySaxParser.getPageListFromXml("xml/pages.xml");
        System.out.println(res);

    }
}
