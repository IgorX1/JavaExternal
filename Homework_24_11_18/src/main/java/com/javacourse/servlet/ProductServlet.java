package com.javacourse.servlet;

import com.javacourse.dao.ProductDAO;
import com.javacourse.model.Product;
import com.javacourse.model.User;
import com.javacourse.servlet.commandManagement.ActionCommand;
import com.javacourse.servlet.commandManagement.productCommands.CRUDProductActionFactory;

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

    //different cases are acceptable: add, update, delete
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processUser(request, response);
    }

    //case when we want to list all the product items
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);

        if(request.getParameter("status")!=null)
            request.setAttribute("error", "Operation Unsuccessful !!!");

        HttpSession session = request.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        if(role== User.ROLE.ADMIN){
            request.getRequestDispatcher("pages/adminview/products_admin.jsp").forward(request, response);
        }else if(role== User.ROLE.USER){
            request.getRequestDispatcher("pages/userview/products_user.jsp").forward(request, response);
        }
    }

    private void processUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String page = null;
        ActionCommand command = CRUDProductActionFactory.defineCommand(request);
        page = command.execute(request);
        if(page!=null){
            response.sendRedirect(page);
        }else {
            response.sendRedirect(request.getContextPath()+"/pages/shared/error_page.jsp");
        }
    }
}
