package com.verbovskiy.finalproject.controller.command;

public class CommandProvider {
    public static ActionCommand defineCommand(String commandName){
        return CommandType.valueOf(commandName.toUpperCase()).getCommand();
    }
}
