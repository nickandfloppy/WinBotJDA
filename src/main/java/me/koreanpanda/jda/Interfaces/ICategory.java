package me.koreanpanda.jda.Interfaces;

import me.koreanpanda.jda.Commands.Global.Commands;

import java.util.List;

public interface ICategory {
    Commands commands = new Commands();
default List<ICommand> importCommands(){return commands.getCommands();}
}
