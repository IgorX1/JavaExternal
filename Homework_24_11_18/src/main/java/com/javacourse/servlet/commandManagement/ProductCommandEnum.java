package com.javacourse.servlet.commandManagement;

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
