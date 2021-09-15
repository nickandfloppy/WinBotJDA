package com.winworldpc.winbot.Commands.Global;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Config;
import net.dv8tion.jda.api.EmbedBuilder;

public class Embeds {
    public Embeds(CommandContext ctx){
        EmbedUtils.setEmbedBuilder(
            ()-> new EmbedBuilder()
                .setColor(Config.EMBED_COLOR)
                //.setFooter("WinBot v0.1", ctx.getSelfMember().getUser().getAvatarUrl())
                //.setTimestamp(Instant.now())
        );
    }
}
