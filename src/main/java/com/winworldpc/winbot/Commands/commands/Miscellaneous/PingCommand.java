package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Config;
import com.winworldpc.winbot.Interfaces.ICommand;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;
import java.util.List;

public class PingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
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
                "Usage: `" + Config.PREFIX + "latency`";
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
