package me.koreanpanda.jda.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;
import java.util.List;

public class PingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
EmbedBuilder embed = EmbedUtils.defaultEmbed();
        jda.getRestPing().queue(
                (ping)-> ctx.getChannel().sendMessageFormat("Rest Ping: %s\nWS Ping: %s", ping, jda.getGatewayPing()).queue()
        );
    }



    @Override
    public String getName() {
        return "latency";
    }

    @Override
    public String getHelp() {
        return "Displays the current latency of the rest, and WS.\n" +
                "Usage: `" + Config.get("prefix") + "latency`";
    }
    @Override
    public String getCategory() {
        return "Miscellaneous";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("ping");
    }
}
