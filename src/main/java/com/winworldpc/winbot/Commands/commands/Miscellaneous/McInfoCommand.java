package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Interfaces.ICommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import com.winworldpc.winbot.Config;

import java.util.Arrays;
import java.util.List;

public class McInfoCommand implements ICommand {
	@Override
	public void handle(CommandContext ctx) {
		TextChannel channel = ctx.getChannel();

		EmbedBuilder embed = EmbedUtils.defaultEmbed();
		embed.setTitle("WinWorldMC");
		embed.addField("Address", "comserv.winworldpc.com (default ports)", true);
		embed.addField("Version", "1.5.2 -> 1.16.5", true);
		//embed.addField("Online?", "a", true);
		//embed.addField("Player Count", "a", true);
		embed.addField("Supports Cracked Accounts?", "No. It never will, just buy the game or stop asking.", true);
		//embed.setThumbnail("https://floppydisk.thisproject.space/img/netmc.png");
		embed.setColor(Config.EMBED_COLOR);
		System.out.println("ass");
		channel.sendMessage(embed.build()).queue();
	}

	@Override
	public String getName() {
		return "mcinfo";
	}

	@Override
	public String getCategory() {
		return "Miscellaneous";
	}

	@Override
	public String getHelp() {
		return "Displays the current information and status of the Minecraft server";
	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList("minecraft");
	}
}
