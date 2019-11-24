package me.koreanpanda.jda.Commands.commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Errors_Music;
import me.koreanpanda.jda.Interfaces.ICommand;
import me.koreanpanda.jda.Music.GuildMusicManager;
import me.koreanpanda.jda.Music.PlayerManager;
import me.koreanpanda.jda.Music.TrackScheduler;
import net.dv8tion.jda.api.entities.TextChannel;

public class SkipCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(ctx.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
        AudioPlayer player = musicManager.player;
        Errors_Music music = new Errors_Music();
        if(player.getPlayingTrack() == null){
            channel.sendMessage(music.Nothing_Is_Playing()).queue();

            return;
        }

        scheduler.nextTrack();

        channel.sendMessage("Skipping the current track").queue();
    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return "Skips the current song";
    }
}
