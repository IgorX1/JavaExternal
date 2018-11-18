package com.javacourse;

import com.javacourse.productModels.Printer;
import com.javacourse.productModels.Product;
import com.javacourse.productsDao.LaptopDAO;
import com.javacourse.productsDao.PrinterDAO;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.math.BigDecimal;

public class App
{
    public static final Logger logger;

    static {
        logger = Logger.getLogger(App.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main( String[] args )
    {
        PrinterDAO printerDAO = new PrinterDAO();

        Printer printer = new Printer();
        printer.setModel("1999");
        printer.setType("Laser");
        printer.setColor("y");
        printer.setPrice(new BigDecimal(200));

        Product product = new Product("1999", "F", "Printer");

        System.out.println(printerDAO.createAsNewProduct(printer, product));
    }
}
