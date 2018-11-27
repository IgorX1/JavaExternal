package com.javacourse.servlet.filter;


import com.javacourse.dao.ProductDAO;
import com.javacourse.dao.UserDAO;
import com.javacourse.model.Product;
import com.javacourse.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//@WebFilter(urlPatterns = {"/controller"})
public class AuthorizationFilter implements Filter {

    private ProductDAO productDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        List<Product> products = productDAO.findAll();
        req.setAttribute("products", products);

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> userDao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("userDao");
        final HttpSession session = req.getSession();
        if(isLoggedIn(session)){
            moveToProductPage(req, res, (User.ROLE)session.getAttribute("role"));
        }else if(doesExistInDB(session, userDao, login, password)){
            User.ROLE role =  userDao.get().getRoleByLoginPassword(login, password);
            session.setAttribute("password", password);
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            moveToProductPage(req, res, role);
        }else{
            moveToProductPage(req, res, User.ROLE.UNKNOWN);
        }
        filterChain.doFilter(req, res);
    }

    boolean isLoggedIn(HttpSession session){
        return (session!=null
                && session.getAttribute("login")!=null
                && session.getAttribute("password")!=null);
    }

    boolean doesExistInDB(HttpSession session, AtomicReference<UserDAO> userDao, String login, String password){
        return userDao.get().userDoesExist(login, password);
    }

    private void moveToProductPage(final HttpServletRequest req,
                                   final HttpServletResponse res,
                                   final User.ROLE role) throws ServletException, IOException {
        if(role == User.ROLE.ADMIN){
            req.getRequestDispatcher("pages/adminview/products_admin.jsp").forward(req, res);
        }else if(role == User.ROLE.USER){
            req.getRequestDispatcher("pages/userview/products_user.jsp").forward(req, res);
        }else {
            req.getRequestDispatcher("pages/shared/access_denied.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
