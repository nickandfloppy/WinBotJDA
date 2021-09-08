package com.winworldpc.winbot.Commands.Global;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;
import java.util.Random;

public class Embeds {
    private final Random random = new Random();
    public Embeds(CommandContext ctx){
        EmbedUtils.setEmbedBuilder(
                ()-> new EmbedBuilder()
                        .setColor(getRandomColor())
                        .setFooter("WinBot v0.1", ctx.getSelfMember().getUser().getAvatarUrl())
                        .setTimestamp(Instant.now())
        );
    }
    private Color getRandomColor(){
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }
}
