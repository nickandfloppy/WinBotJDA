package com.winworldpc.winbot.Interfaces;

import com.winworldpc.winbot.Commands.Global.Commands;

import java.util.List;

public interface ICategory {
    Commands commands = new Commands();
default List<ICommand> importCommands(){return commands.getCommands();}
}
