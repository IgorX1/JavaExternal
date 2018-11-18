package com.javacourse.queryController;

import com.javacourse.productsDao.PcDAO;

public class ShowAllPcCommand implements Command{
    private PcDAO pcDAO;

    public ShowAllPcCommand(PcDAO pcDAO) {
        this.pcDAO = pcDAO;
    }

    @Override
    public void execute() {
        System.out.println(pcDAO.findAll());
    }
}
