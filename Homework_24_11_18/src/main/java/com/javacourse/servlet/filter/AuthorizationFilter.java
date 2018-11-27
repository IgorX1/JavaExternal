package com.javacourse.servlet.filter;


import com.javacourse.dao.ProductDAO;
import com.javacourse.dao.UserDAO;
import com.javacourse.model.Product;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@WebFilter(urlPatterns = {"/*"})
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
        final HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/login";

        List<Product> products = productDAO.findAll();
        req.setAttribute("products", products);

        if(isLoggedIn(session) || isLoginRequest(req, loginURI)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            res.sendRedirect(loginURI);
        }
    }

    boolean isLoggedIn(HttpSession session){
        return (session!=null
                && session.getAttribute("login")!=null
                && session.getAttribute("password")!=null);
    }

    boolean isLoginRequest(HttpServletRequest request, String loginRequestUri){
       String r = request.getRequestURI();
       return request.getRequestURI().equals(loginRequestUri);
    }

    boolean doesExistInDB(HttpSession session, AtomicReference<UserDAO> userDao, String login, String password){
        return userDao.get().userDoesExist(login, password);
    }


    @Override
    public void destroy() {

    }
}
