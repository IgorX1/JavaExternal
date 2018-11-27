package com.javacourse.servlet.commandManagement;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand{
    String execute(HttpServletRequest request);
}
