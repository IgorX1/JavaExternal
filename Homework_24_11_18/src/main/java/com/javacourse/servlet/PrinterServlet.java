package com.javacourse.servlet;

import com.javacourse.dao.PrinterDAO;
import com.javacourse.model.Printer;
import com.javacourse.model.User;
import com.javacourse.servlet.commandManagement.ActionCommand;
import com.javacourse.servlet.commandManagement.printerCommands.CRUDPrinterActionFactory;
import com.javacourse.servlet.commandManagement.productCommands.CRUDProductActionFactory;

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
        processUser(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Printer> printers = printerDAO.findAll();
        request.setAttribute("printers", printers);

        if(request.getParameter("status")!=null)
            request.setAttribute("error", "Operation Unsuccessful !!!");

        HttpSession session =  request.getSession();
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        if(role == User.ROLE.ADMIN){
            request.getRequestDispatcher("pages/adminview/printers_admin.jsp").forward(request, response);
        }else if(role == User.ROLE.USER){
            request.getRequestDispatcher("pages/userview/printers_user.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("pages/shared/access_denied.jsp").forward(request, response);
        }
    }

    private void processUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = null;
        ActionCommand command = CRUDPrinterActionFactory.defineCommand(request);
        page = command.execute(request);
        if(page!=null){
            response.sendRedirect(page);
        }else {
            response.sendRedirect(request.getContextPath()+"/pages/shared/error_page.jsp");
        }
    }
}
