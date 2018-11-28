package com.javacourse.servlet.commandManagement;

import com.javacourse.servlet.commandManagement.productCommands.EmptyProductCommand;
import com.javacourse.servlet.commandManagement.productCommands.ProductCommandEnum;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public static ActionCommand defineCommand(final HttpServletRequest request){
        ActionCommand current = new EmptyProductCommand();
        String action = request.getParameter("command");
        if(action==null || action.isEmpty()){
            return current;
        }
        try{
            ProductCommandEnum currentEnum = ProductCommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        }catch (IllegalArgumentException e){
            request.setAttribute("wrongAction", "Your action is wrong");
        }
        return current;
    }
}
