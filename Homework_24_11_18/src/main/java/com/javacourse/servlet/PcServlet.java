package com.javacourse.servlet;

import com.javacourse.dao.PcDAO;
import com.javacourse.model.Pc;
import com.javacourse.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PcServlet", urlPatterns = {"/PcServlet"})
public class PcServlet extends HttpServlet {

    private PcDAO pcDAO;

    @Override
    public void init() throws ServletException {
        pcDAO = new PcDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pc> pcs = pcDAO.findAll();
        request.setAttribute("pcs", pcs);

        HttpSession session =  request.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        if(role == User.ROLE.ADMIN){
            request.getRequestDispatcher("pages/adminview/pcs_admin.jsp").forward(request, response);
        }else if(role == User.ROLE.USER){
            request.getRequestDispatcher("pages/userview/pcs_user.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("pages/shared/access_denied.jsp").forward(request, response);
        }
    }
}
