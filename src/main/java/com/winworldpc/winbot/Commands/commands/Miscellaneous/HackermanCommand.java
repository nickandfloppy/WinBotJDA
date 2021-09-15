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
import java.util.Random;

public class HackermanCommand implements ICommand {
	@Override
	public void handle(CommandContext ctx) {
		Integer length;
		List<String> args = ctx.getArgs();
		if (args.isEmpty()) { length = 6; }
		else { length = Integer.parseInt(args.get(0)); }

		List<String> things = Arrays.asList("non-rotatable disk", "side fumbling CPU", "processor", "with multidimension network security access vulnerabilities", "oc6 level optical line", "microprocessor architecture", "server", "minecraft server",	"webserver running Linux 0.01", "Linux system", "shell access terminals", "vulnerable networking firewall",	"multiphase process memorizer", "x86 IBM level architecture", "network firewall daemon", "network routing device",	"insecure Windows server", "Windows server 1985", "transdimensional phasing device");

		List<String> actions = Arrays.asList("I've hacked into your ", "I'm breaking into the ", "I've hacked the ", "I've gained effective root access to your ", "I'm gaining root access to ", "I've hacked the ", "I broke into the ");

		Random rand = new Random();
		String jargon;
		TextChannel channel = ctx.getChannel();

		if (length < 6) {
			System.out.println("ass 1");
		}

		else if (length > 100) {
			System.out.println("ass 2");
		}

		else {
			jargon = actions.get(rand.nextInt(actions.size()));
			for (int i = 0; i < length; i++) {
				if (i == 1) {
					jargon += "with ";
					continue;
				}
				jargon += (things.get(rand.nextInt(things.size())) + " ");
			}
			EmbedBuilder embed = EmbedUtils.defaultEmbed();
			embed.setDescription("```cpp\n" + jargon + "```");
			embed.setColor(Config.EMBED_COLOR);

			channel.sendMessage(embed.build()).queue();
		}
	}

	@Override
	public String getName() {
		return "hackerman";
	}

	@Override
	public String getCategory() {
		return "Miscellaneous";
	}

	@Override
	public String getHelp() {
		return "succ";
	}
}
