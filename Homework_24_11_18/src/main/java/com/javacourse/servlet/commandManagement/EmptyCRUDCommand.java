package com.javacourse.servlet.commandManagement;

import com.javacourse.servlet.commandManagement.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCRUDCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getContextPath() + "/pages/login.jsp";
        return page;
    }
}
