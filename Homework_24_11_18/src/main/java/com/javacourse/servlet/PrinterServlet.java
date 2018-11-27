package com.javacourse.servlet;

import com.javacourse.dao.PrinterDAO;
import com.javacourse.model.Printer;
import com.javacourse.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrinterServlet", urlPatterns = {"/PrinterServlet"})
public class PrinterServlet extends HttpServlet {
    private PrinterDAO printerDAO;

    @Override
    public void init() throws ServletException {
        printerDAO = new PrinterDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Printer> printers = printerDAO.findAll();
        request.setAttribute("printers", printers);

        HttpSession session =  request.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        if(role == User.ROLE.ADMIN){
            request.getRequestDispatcher("pages/adminview/printers_admin.jsp").forward(request, response);
        }else if(role == User.ROLE.USER){
            request.getRequestDispatcher("pages/userview/printers_admin.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("pages/shared/access_denied.jsp").forward(request, response);
        }
    }
}
