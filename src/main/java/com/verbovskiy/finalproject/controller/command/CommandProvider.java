package com.verbovskiy.finalproject.controller.command;

public class CommandProvider {
    public ActionCommand defineCommand(String commandName){
        return CommandType.valueOf(commandName.toUpperCase()).getCommand();
    }
}
