package me.koreanpanda.jda.Commands.Category;

import me.koreanpanda.jda.Commands.commands.Music.*;
import me.koreanpanda.jda.Interfaces.ICategory;

public class Music implements ICategory {
    public Music(){
        commands.addCommand(new JoinCommand());
        commands.addCommand(new LeaveCommand());
        commands.addCommand(new NowPlayingCommand());
        commands.addCommand(new PlayCommand());
        commands.addCommand(new QueueCommand());
        commands.addCommand(new SeekCommand());
        commands.addCommand(new SkipCommand());
        commands.addCommand(new StopCommand());
    }
}
