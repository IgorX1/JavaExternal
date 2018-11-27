package com.javacourse.servlet;

import com.javacourse.dao.UserDAO;
import com.javacourse.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class AuthorizationServlet extends HttpServlet {
    //case when user submits authorization form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> userDao = (AtomicReference<UserDAO>) request.getServletContext().getAttribute("userDao");
        if (userDao.get().userDoesExist(userLogin, userPassword)) {
            session.setAttribute("login", userLogin);
            session.setAttribute("password", userPassword);
            if(userDao.get().getRoleByLoginPassword(userLogin, userPassword)== User.ROLE.ADMIN){
                session.setAttribute("role", User.ROLE.ADMIN);
                //response.sendRedirect("pages/adminview/products_admin.jsp");
            }else if(userDao.get().getRoleByLoginPassword(userLogin, userPassword)== User.ROLE.USER){
                session.setAttribute("role", User.ROLE.USER);
                //response.sendRedirect("pages/userview/products_user.jsp");
            }
            response.sendRedirect("/ProductServlet");
        }else {
            response.sendRedirect("/pages/login.jsp");
        }
    }

    //case when user is unauthorized user is redirected to logging page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
