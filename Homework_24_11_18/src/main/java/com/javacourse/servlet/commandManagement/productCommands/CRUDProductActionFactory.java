package com.javacourse.servlet.commandManagement.productCommands;

import com.javacourse.servlet.commandManagement.ActionCommand;
import com.javacourse.servlet.commandManagement.EmptyCRUDCommand;
import com.javacourse.servlet.commandManagement.productCommands.ProductCommandEnum;

import javax.servlet.http.HttpServletRequest;

public class CRUDProductActionFactory {
    public static ActionCommand defineCommand(final HttpServletRequest request){
        ActionCommand current = new EmptyCRUDCommand();
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
