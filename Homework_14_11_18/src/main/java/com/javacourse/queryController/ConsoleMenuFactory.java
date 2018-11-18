package com.javacourse.queryController;

import com.javacourse.productsDao.LaptopDAO;
import com.javacourse.productsDao.PcDAO;
import com.javacourse.productsDao.PrinterDAO;
import com.javacourse.productsDao.ProductDAO;

public class ConsoleMenuFactory {
    public static MenuController getMenuController(){
        MenuController menuController = new MenuController();

        LaptopDAO laptopDAO = new LaptopDAO();
        PcDAO pcDAO = new PcDAO();
        PrinterDAO printerDAO = new PrinterDAO();
        ProductDAO productDAO = new ProductDAO();

        ShowAllLaptopsCommand showAllLaptopsCommand = new ShowAllLaptopsCommand(laptopDAO);
        ShowAllPcCommand showAllPcCommand = new ShowAllPcCommand(pcDAO);
        ShowAllPrinterCommand showAllPrinterCommand = new ShowAllPrinterCommand(printerDAO);
        ShowAllProductCommand showAllProductCommand = new ShowAllProductCommand(productDAO);

        ShowLaptopByKeyCommand showLaptopByKeyCommand = new ShowLaptopByKeyCommand(laptopDAO);
        ShowPcByKeyCommand showPcByKeyCommand = new ShowPcByKeyCommand(pcDAO);
        ShowPrinterByKeyCommand showPrinterByKeyCommand = new ShowPrinterByKeyCommand(printerDAO);
        ShowProductByKeyCommand showProductByKeyCommand = new ShowProductByKeyCommand(productDAO);

        menuController.setCommand(0, showAllLaptopsCommand);
        menuController.setCommand(1, showAllPcCommand);
        menuController.setCommand(2, showAllPrinterCommand);
        menuController.setCommand(3, showAllProductCommand);

        menuController.setCommand(4, showLaptopByKeyCommand);
        menuController.setCommand(5, showPcByKeyCommand);
        menuController.setCommand(6, showPrinterByKeyCommand);
        menuController.setCommand(7, showProductByKeyCommand);
        return menuController;
    }
}
