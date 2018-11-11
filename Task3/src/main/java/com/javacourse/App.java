package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.List;

public class App {
    public static final Logger logger;

    static {
        logger = Logger.getLogger(App.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main(String[] args) {
        XMLParser xmlParser = new MyDOMParser();
        List<Page> res1 = xmlParser.getPageListFromXml("xml/pages.xml");
        System.out.println(res1);

        xmlParser = new MySAXParser();
        List<Page> res2 = xmlParser.getPageListFromXml("xml/pages.xml");
        System.out.println(res2);

        xmlParser = new MyStAXParser();
        List<Page> res3 = xmlParser.getPageListFromXml("xml/pages.xml");
        System.out.println(res3);

        HTMLTransformer.transformToHTML("xml/styles.xsl",
                "xml/pages.xml",
                "xml/res.html");

    }
}
