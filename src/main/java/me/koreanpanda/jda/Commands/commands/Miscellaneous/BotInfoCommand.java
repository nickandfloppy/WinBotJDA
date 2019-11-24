package me.koreanpanda.jda.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.List;

public class BotInfoCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();

        EmbedBuilder embed = EmbedUtils.defaultEmbed();
        embed.setTitle("Panda 2.0");
        StringBuilder str = new StringBuilder();
        str
                .append("**Bio**: I am a panda. ^-^ I like bamboo, and 1's, as they look like bamboo.\n")
                .append("**Born**: ").append(ctx.getSelfMember().getUser().getTimeCreated()).append("\n")
                .append("**Parent**: "). append("koreanpanda345#2787").append("\n")
                .append("**Race**: "). append("JDA").append("\n");
        embed.setDescription(str.toString());
        embed.setThumbnail(ctx.getSelfUser().getAvatarUrl());

        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getName() {
        return "botinfo";
    }

    @Override
    public String getCategory() {
        return "Information";
    }

    @Override
    public String getHelp() {
        return "Displays information about me.";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("info", "bot");
    }
}
