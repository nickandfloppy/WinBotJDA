package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Config;
import com.winworldpc.winbot.Interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;


public class CatCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        WebUtils.ins.scrapeWebPage("https://api.thecatapi.com/api/images/get?format=xml&results_per_page=1").async((document)->{
                String url = document.getElementsByTag("url").first().html();
                EmbedBuilder embed = EmbedUtils.embedImage(url);
                embed.setColor(Config.EMBED_COLOR);
                ctx.getChannel().sendMessage(embed.build()).queue();
        });
    }

    @Override
    public String getName() {
        return "cat";
    }

    @Override
    public String getCategory() {
        return "Miscellaneous";
    }

    @Override
    public String getHelp() {
        return "Shows you a random cat.";
    }
}
