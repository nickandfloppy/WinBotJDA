package me.koreanpanda.jda.Commands.commands.Music;

import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Errors_Music;
import me.koreanpanda.jda.Commands.Global.Errors_Permissions;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        AudioManager audioManager = ctx.getGuild().getAudioManager();
        Errors_Permissions perms = new Errors_Permissions();
        Errors_Music music = new Errors_Music();
        if(audioManager.isConnected()){
            channel.sendMessage("I'm already connected to a channel").queue();
            return;
        }

        GuildVoiceState memberVoiceState = ctx.getMember().getVoiceState();
        //TODO: need to make a Music Error file.
        if(!memberVoiceState.inVoiceChannel()){
            channel.sendMessage(music.Not_In_A_Voice_Channel()).queue();
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = ctx.getGuild().getSelfMember();
        if(!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)){
            perms.Self_No_Permissions("Voice Connect");
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        channel.sendMessage("Joining your voice channel").queue();
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return "Makes the bot join your voice channel";
    }
}
