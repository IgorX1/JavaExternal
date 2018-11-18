package com.javacourse.queryController;

import com.javacourse.productsDao.PrinterDAO;

public class ShowAllPrinterCommand implements Command{
    private PrinterDAO printerDAO;

    public ShowAllPrinterCommand(PrinterDAO printerDAO) {
        this.printerDAO = printerDAO;
    }

    @Override
    public void execute() {
        System.out.println(printerDAO.findAll());
    }
}
