package com.winworldpc.winbot.Commands.Category;

import com.winworldpc.winbot.CommandManager;
import com.winworldpc.winbot.Commands.commands.Miscellaneous.*;
import com.winworldpc.winbot.Interfaces.ICategory;

public class Miscellaneous implements ICategory {
    public Miscellaneous(CommandManager manager){
        commands.addCommand(new CatCommand());
        commands.addCommand(new DiceCommand());
        commands.addCommand(new HelpCommand(manager));
        commands.addCommand(new PingCommand());
        commands.addCommand(new UptimeCommand());
        commands.addCommand(new WhoisCommand());
        commands.addCommand(new ServerInfoCommand());
        commands.addCommand(new AboutCommand());
    }
}
