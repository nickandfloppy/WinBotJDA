package me.koreanpanda.jda.Commands.commands.Music;

import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        AudioManager audioManager = ctx.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {
            channel.sendMessage("I'm not connected to a voice channel").queue();
            return;
        }

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        if (!voiceChannel.getMembers().contains(ctx.getMember())) {
            channel.sendMessage("You have to be in the same voice channel as me to use this").queue();
            return;
        }

        audioManager.closeAudioConnection();
        channel.sendMessage("Disconnected from your channel").queue();
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return "Makes the bot leave your voice channel.";
    }
}
