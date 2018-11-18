package com.javacourse.queryController;

import com.javacourse.productsDao.ProductDAO;

public class ShowAllProductCommand implements Command {
    private ProductDAO productDAO;

    public ShowAllProductCommand(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void execute() {
        System.out.println(productDAO.findAll());
    }
}
