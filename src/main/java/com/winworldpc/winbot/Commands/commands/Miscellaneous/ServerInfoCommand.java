package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Config;
import com.winworldpc.winbot.Interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;

import java.time.format.DateTimeFormatter;

public class ServerInfoCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();

        String generalInfo = String.format(
                "**Owner**: <@%s>\n**Region**: %s\n**Creation Date**: %s\n**Verification Level**: %s",
                guild.getOwnerId(),
                guild.getRegion().getName(),
                guild.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME),
                convertVerificationLevel(guild.getVerificationLevel())

        );

        String memberInfo = String.format(
          "**Total Roles**: %s\n**Total Members**: %s\n**Online Members**: %s\n**Offline Members**: %s\n**Bot Count**: %s",
                guild.getRoleCache().size(),
                guild.getMemberCache().size(),
                guild.getMemberCache().stream().filter((m)-> m.getOnlineStatus() == OnlineStatus.ONLINE).count(),
                guild.getMemberCache().stream().filter((m) -> m.getOnlineStatus() == OnlineStatus.OFFLINE).count(),
                guild.getMemberCache().stream().filter((m)-> m.getUser().isBot()).count()
        );

        EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setTitle("Server Info for " + guild.getName())
                .setThumbnail(guild.getIconUrl())
                .addField("General Info", generalInfo, false)
                .addField("Role and Member Counts", memberInfo, false)
                .setColor(Config.EMBED_COLOR);
            ctx.getChannel().sendMessage(embed.build()).queue();
    }
    private String convertVerificationLevel(Guild.VerificationLevel lvl){
        String[] names = lvl.name().toLowerCase().split("_");
        StringBuilder out = new StringBuilder();

        for(String name : names){
            out.append(Character.toUpperCase(getName().charAt(0))).append(name.substring(1)).append(" ");
        }

        return out.toString().trim();
    }
    @Override
    public String getName() {
        return "serverinfo";
    }

    @Override
    public String getCategory() {
        return "Information";
    }

    @Override
    public String getHelp() {
        return "Shows you information about the server.";
    }


}
