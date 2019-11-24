package me.koreanpanda.jda.Commands.Category;

import me.koreanpanda.jda.Commands.commands.Moderation.BanCommand;
import me.koreanpanda.jda.Commands.commands.Moderation.KickCommand;
import me.koreanpanda.jda.Commands.commands.Moderation.UnbanCommand;
import me.koreanpanda.jda.Interfaces.ICategory;

public class Moderation implements ICategory {
    public Moderation(){
        commands.addCommand(new BanCommand());
        commands.addCommand(new KickCommand());
        commands.addCommand(new UnbanCommand());
    }
}
