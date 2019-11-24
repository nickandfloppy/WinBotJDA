package me.koreanpanda.jda.Commands.commands.Music;

import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Interfaces.ICommand;
import me.koreanpanda.jda.Music.GuildMusicManager;
import me.koreanpanda.jda.Music.PlayerManager;

public class StopCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(ctx.getGuild());

        musicManager.scheduler.getQueue().clear();
        musicManager.player.stopTrack();
        musicManager.player.setPaused(false);

        ctx.getChannel().sendMessage("Stopping the player and clearing the queue").queue();
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return "Stops the music player.";
    }
}
