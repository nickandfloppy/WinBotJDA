package me.koreanpanda.jda.Commands.Category;

import me.koreanpanda.jda.CommandManager;
import me.koreanpanda.jda.Commands.commands.Miscellaneous.*;
import me.koreanpanda.jda.Interfaces.ICategory;

public class Miscellaneous implements ICategory {
    public Miscellaneous(CommandManager manager){
        commands.addCommand(new CatCommand());
        commands.addCommand(new DiceCommand());
        commands.addCommand(new HelpCommand(manager));
        commands.addCommand(new PingCommand());
        commands.addCommand(new UptimeCommand());
        commands.addCommand(new WhoisCommand());
        commands.addCommand(new ServerInfoCommand());
        commands.addCommand(new BotInfoCommand());
    }
}
