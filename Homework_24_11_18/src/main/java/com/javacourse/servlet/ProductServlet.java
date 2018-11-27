package com.javacourse.servlet;

import com.javacourse.dao.ProductDAO;
import com.javacourse.model.Product;
import com.javacourse.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns="/ProductServlet")
public class ProductServlet extends HttpServlet {
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

        HttpSession session = request.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        if(role== User.ROLE.ADMIN){
            request.getRequestDispatcher("pages/adminview/products_admin.jsp").forward(request, response);
        }else if(role== User.ROLE.USER){
            request.getRequestDispatcher("pages/userview/products_user.jsp").forward(request, response);
        }
    }
}