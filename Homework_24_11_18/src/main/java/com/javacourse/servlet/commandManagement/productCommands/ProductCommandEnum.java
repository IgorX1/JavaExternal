package com.javacourse.servlet.commandManagement.productCommands;

import com.javacourse.servlet.commandManagement.ActionCommand;

public enum ProductCommandEnum {
    ADD{
        {
            this.command = new AddProductCommand();
        }
    },
    DELETE{
        {
            this.command = new DeleteProductCommand();
        }
    },
    UPDATE{
        {
            this.command = new UpdateProductCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand(){
        return command;
    }
}
