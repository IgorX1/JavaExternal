package com.javacourse.queryController;

import com.javacourse.productsDao.LaptopDAO;

public class ShowAllLaptopsCommand implements Command{
    private LaptopDAO laptopDAO;

    public ShowAllLaptopsCommand(LaptopDAO laptopDAO) {
        this.laptopDAO = laptopDAO;
    }

    @Override
    public void execute() {
        System.out.println(laptopDAO.findAll());
    }
}
