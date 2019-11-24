package me.koreanpanda.jda.Commands.Category;

import me.koreanpanda.jda.Commands.commands.Admin.SetPrefixCommand;
import me.koreanpanda.jda.Interfaces.ICategory;


public class Admin implements ICategory {
    public Admin(){
        commands.addCommand(new SetPrefixCommand());
    }
}
