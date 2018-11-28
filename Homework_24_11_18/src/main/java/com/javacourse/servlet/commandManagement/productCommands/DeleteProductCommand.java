package com.javacourse.servlet.commandManagement.productCommands;

import com.javacourse.dao.ProductDAO;
import com.javacourse.servlet.commandManagement.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductCommand implements ActionCommand {

    ProductDAO productDAO = new ProductDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if(productDAO.delete(id)){
            return "/ProductServlet";
        }else {
            return "/ProductServlet?status=unsuccessful";
        }
    }
}
