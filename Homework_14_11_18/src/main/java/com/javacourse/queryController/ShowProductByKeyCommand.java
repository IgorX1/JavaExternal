package com.javacourse.queryController;

import com.javacourse.productsDao.ProductDAO;

public class ShowProductByKeyCommand implements Command{
    ProductDAO productDAO;

    public ShowProductByKeyCommand(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void execute() {
        System.out.println(productDAO.findById(ConsoleInputManager.getStringKeyFromUser()));
    }
}
