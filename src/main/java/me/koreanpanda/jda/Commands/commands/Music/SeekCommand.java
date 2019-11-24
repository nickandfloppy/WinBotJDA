package me.koreanpanda.jda.Commands.commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Error;
import me.koreanpanda.jda.Commands.Global.Errors_Music;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import me.koreanpanda.jda.Music.GuildMusicManager;
import me.koreanpanda.jda.Music.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;

import java.util.List;

import java.util.concurrent.TimeUnit;

public class SeekCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(ctx.getGuild());
        AudioPlayer player = musicManager.player;
        Error error = new Error();
        Errors_Music music = new Errors_Music();

        if(args.isEmpty()){
            channel.sendMessage(error.Missing_Argument());

            return;
        }

        player.getPlayingTrack().setPosition(getMilliseconds(args.get(0)));

        channel.sendMessage("Successful skipped to " + args.get(0)).queue();


    }

    public long getMilliseconds(String time){
        List<String> sections = Arrays.asList(time.split(":"));
        long hour = Long.parseLong(sections.get(0));
        long minutes = Long.parseLong(sections.get(1));
        long seconds = Long.parseLong(sections.get(2));

        long millihour = hour * TimeUnit.MILLISECONDS.toMillis(1);
        long millimin = minutes * TimeUnit.MINUTES.toMillis(1);
        long millisec = seconds % TimeUnit.MINUTES.toMillis(1) *  TimeUnit.SECONDS.toMillis(1);

        return millihour + millimin + millisec;
    }

    @Override
    public String getName() {
        return "seek";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return "Allows you to skip to a certain point in the song.\n" +
                "Usage: " + Config.get("prefix") + getName() + "<time(hh:mm:ss>";
    }
}
