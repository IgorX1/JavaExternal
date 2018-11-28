package com.javacourse.servlet.commandManagement.productCommands;

import com.javacourse.dao.ProductDAO;
import com.javacourse.model.Product;
import com.javacourse.servlet.commandManagement.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class AddProductCommand implements ActionCommand {
    ProductDAO productDAO = new ProductDAO();

    private static final String MAKER_PARAM = "maker";
    private static final String MODEL_PARAM = "model";
    private static final String TYPE_PARAM = "type";

    @Override
    public String execute(HttpServletRequest request) {
        String maker = request.getParameter(MAKER_PARAM);
        String model = request.getParameter(MODEL_PARAM);
        String  type = request.getParameter(TYPE_PARAM);

        Product product = new Product(model, maker, type);
        if(productDAO.create(product)){
            return "/ProductServlet";
        }else{
            return "/ProductServlet?status=unsuccessful";
        }
    }
}
