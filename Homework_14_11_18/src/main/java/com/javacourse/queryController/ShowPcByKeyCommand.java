package com.javacourse.queryController;

import com.javacourse.productsDao.PcDAO;

public class ShowPcByKeyCommand implements Command {
    PcDAO pcDAO;

    public ShowPcByKeyCommand(PcDAO pcDAO) {
        this.pcDAO = pcDAO;
    }

    @Override
    public void execute() {
        System.out.println(pcDAO.findById(ConsoleInputManager.getKeyFromUser()));
    }
}
