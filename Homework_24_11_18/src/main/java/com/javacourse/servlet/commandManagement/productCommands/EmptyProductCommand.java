package com.javacourse.servlet.commandManagement.productCommands;

import com.javacourse.servlet.commandManagement.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyProductCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getContextPath() + "/pages/login.jsp";
        return page;
    }
}
