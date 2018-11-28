package com.javacourse.servlet.commandManagement.printerCommands;

import com.javacourse.servlet.commandManagement.ActionCommand;
import com.javacourse.servlet.commandManagement.productCommands.AddProductCommand;
import com.javacourse.servlet.commandManagement.productCommands.DeleteProductCommand;
import com.javacourse.servlet.commandManagement.productCommands.UpdateProductCommand;

public enum PrinterCommandEnum {
        ADD{
            {
                this.command = new AddPrinterCommand();
            }
        },
        DELETE{
            {
                this.command = new DeletePrinterCommand();
            }
        },
        UPDATE{
            {
                this.command = new UpdatePrinterCommand();
            }
        };

        ActionCommand command;
        public ActionCommand getCurrentCommand(){
            return command;
        }
}
