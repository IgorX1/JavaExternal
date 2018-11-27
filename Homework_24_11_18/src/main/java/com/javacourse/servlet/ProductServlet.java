package com.javacourse.servlet;

import com.javacourse.dao.ProductDAO;
import com.javacourse.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns="/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final String PRODUCT_LIST_PAGE = "products_user.jsp";
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher(PRODUCT_LIST_PAGE).forward(request,response);
        response.getWriter().write("asdasdas");
        response.getWriter().flush();
    }
}
