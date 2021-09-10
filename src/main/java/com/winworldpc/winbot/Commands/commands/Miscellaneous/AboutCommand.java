package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import com.winworldpc.winbot.Config;

import java.util.Arrays;
import java.util.List;

public class AboutCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();

        EmbedBuilder embed = EmbedUtils.defaultEmbed();
        embed.setTitle("WinBot v0.1");
        StringBuilder str = new StringBuilder();
        str
                .append("**About**: A Discord bot made specifically for use in the WinWorld server .\n")
                .append("**Created**: ").append(ctx.getSelfMember().getUser().getTimeCreated()).append("\n")
                .append("**Owner**: "). append("floppydisk#0590").append("\n")
                .append("**Library**: "). append("JDA").append("\n");
        embed.setDescription(str.toString());
        embed.setThumbnail(ctx.getSelfUser().getAvatarUrl());
        embed.setColor(Config.EMBED_COLOR);

        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getName() {
        return "about";
    }

    @Override
    public String getCategory() {
        return "Information";
    }

    @Override
    public String getHelp() {
        return "Displays information about the bot.";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("info", "bot");
    }
}
