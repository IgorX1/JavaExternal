package com.javacourse.queryController;

import com.javacourse.productsDao.LaptopDAO;

public class ShowLaptopByKeyCommand implements Command {

    private LaptopDAO laptopDAO;

    public ShowLaptopByKeyCommand(LaptopDAO laptopDAO) {
        this.laptopDAO = laptopDAO;
    }

    @Override
    public void execute() {
        System.out.println(laptopDAO.findById(ConsoleInputManager.getKeyFromUser()));
    }

}
