package com.javacourse.queryController;

import com.javacourse.productsDao.PrinterDAO;

public class ShowPrinterByKeyCommand implements Command {
    PrinterDAO printerDAO = new PrinterDAO();

    public ShowPrinterByKeyCommand(PrinterDAO printerDAO) {
        this.printerDAO = printerDAO;
    }

    @Override
    public void execute() {
        System.out.println(printerDAO.findById(ConsoleInputManager.getKeyFromUser()));
    }
}
