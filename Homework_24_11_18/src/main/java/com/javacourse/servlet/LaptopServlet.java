package com.javacourse.servlet;

import com.javacourse.dao.LaptopDAO;
import com.javacourse.model.Laptop;
import com.javacourse.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LaptopServlet", urlPatterns = {"/LaptopServlet"})
public class LaptopServlet extends HttpServlet {

    private LaptopDAO laptopDAO;

    @Override
    public void init() throws ServletException {
        laptopDAO = new LaptopDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Laptop> laptops = laptopDAO.findAll();
        request.setAttribute("laptops", laptops);

        HttpSession session =  request.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        if(role == User.ROLE.ADMIN){
            request.getRequestDispatcher("pages/adminview/laptops_admin.jsp").forward(request, response);
        }else if(role == User.ROLE.USER){
            request.getRequestDispatcher("pages/userview/laptops_user.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("pages/shared/access_denied.jsp").forward(request, response);
        }
    }
}
