package com.javacourse.queryController;

public class MenuController {
    private static final int COMMAND_COUNT = 8;
    private Command[] commands;

    public MenuController() {
        commands = new Command[COMMAND_COUNT];
        Command noCommand = new NoCommand();
        for(int slot=0; slot<COMMAND_COUNT; ++slot){
            commands[slot] = noCommand;
        }
    }

    public void setCommand(int slot, Command cmd){
        commands[slot] = cmd;
    }

    public void menuItemPickedUp(int slot){
        commands[slot].execute();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("~~~~~~~~~~MENU~~~~~~~~~~\n");
        for(int i=0; i<commands.length; ++i){
            sb.append("[slot "+i+"] "+commands[i].getClass().getSimpleName()+'\n');
        }
        return sb.toString();
    }
}
