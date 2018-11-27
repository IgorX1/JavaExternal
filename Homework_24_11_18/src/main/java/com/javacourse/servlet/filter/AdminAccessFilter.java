package com.javacourse.servlet.filter;

import com.javacourse.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/adminview/*"})
public class AdminAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if(isLoggedIn(session)){
            User.ROLE role = (User.ROLE) session.getAttribute("role");
            //when USER tries to access ADMIN's pages
            if(role != User.ROLE.ADMIN){
                response.sendRedirect(request.getContextPath() + "/pages/shared/access_denied.jsp");
            }else filterChain.doFilter(servletRequest, servletResponse);
        }else {
            //case when user is not authorized
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    boolean isLoggedIn(HttpSession session){
        return (session!=null
                && session.getAttribute("login")!=null
                && session.getAttribute("password")!=null);
    }
}
