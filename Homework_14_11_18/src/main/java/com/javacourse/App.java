package com.javacourse;

import com.javacourse.productDao.LaptopDAO;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class App
{
    public static final Logger logger;

    static {
        logger = Logger.getLogger(App.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main( String[] args )
    {
        LaptopDAO ld = new LaptopDAO();
        System.out.println(ld.findById(1));
    }
}
