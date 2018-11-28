package com.javacourse.servlet.commandManagement.printerCommands;

import com.javacourse.dao.PrinterDAO;

import javax.servlet.http.HttpServletRequest;

public class DeletePrinterCommand implements com.javacourse.servlet.commandManagement.ActionCommand {
    PrinterDAO printerDAO = new PrinterDAO();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        if(printerDAO.delete(id)){
            return "/PrinterServlet";
        }else {
            return "/PrinterServlet?status=unsuccessful";
        }
    }
}
