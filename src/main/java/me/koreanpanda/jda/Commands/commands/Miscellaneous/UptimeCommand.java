package me.koreanpanda.jda.Commands.commands.Miscellaneous;

import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Interfaces.ICommand;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class UptimeCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeMXBean.getUptime();
        long uptimeInSeconds = uptime/1000;
        long numberOfHours = uptimeInSeconds / (60 * 60);
        long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = (uptimeInSeconds % 60);

        ctx.getChannel().sendMessageFormat(
                "My uptime is `%s hours, %s minutes, %s seconds`",
                numberOfHours, numberOfMinutes, numberOfSeconds
        ).queue();
    }

    @Override
    public String getName() {
        return "uptime";
    }

    @Override
    public String getCategory() {
        return "Information";
    }

    @Override
    public String getHelp() {
        return "Shows the current uptime of the bot.";
    }
}
