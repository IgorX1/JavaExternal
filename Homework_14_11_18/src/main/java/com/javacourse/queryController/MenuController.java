package com.javacourse.queryController;

public class MenuController {
    private static final int COMMAND_COUNT = 5;
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

    public void onMenuItemWasPickedUp(int slot){
        commands[slot].execute();
    }
}
