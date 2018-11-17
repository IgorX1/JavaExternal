package com.javacourse;

import com.javacourse.productDao.LaptopDAO;
import com.javacourse.productDao.PrinterDAO;
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
        LaptopDAO laptopDao = new LaptopDAO();
        PrinterDAO printerDAO = new PrinterDAO();
        System.out.println(laptopDao.getLaptopById(1));
        System.out.println(printerDAO.getPrinterById(1));
    }
}
