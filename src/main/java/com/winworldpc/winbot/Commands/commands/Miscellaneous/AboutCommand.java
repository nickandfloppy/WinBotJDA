package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Interfaces.ICommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
//import net.dv8tion.jda.api.entities.ApplicationInfo;
import com.winworldpc.winbot.Config;

import java.util.Arrays;
import java.util.List;

public class AboutCommand implements ICommand {
	@Override
	public void handle(CommandContext ctx) {
		String verString = String.format("v%s", Config.VERSION);
		String osVerString = String.format("%s %s", System.getProperty("os.name"), System.getProperty("os.version"));
		TextChannel channel = ctx.getChannel();

		EmbedBuilder embed = EmbedUtils.defaultEmbed();
		embed.setTitle("WinBot", "https://github.com/floppydisk05/WinBotJDA");
		// embed.addField("Maintainer", ApplicationInfo.getOwner(), true); broken
		embed.addField("Contributors", "nick99nack\nfloppydisk", true);
		embed.addField("Language", "Java", true);
		embed.addField("Version", verString, true);
		embed.addField("Library", "Java Discord API", true);
		embed.addField("Host", osVerString, true);
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
