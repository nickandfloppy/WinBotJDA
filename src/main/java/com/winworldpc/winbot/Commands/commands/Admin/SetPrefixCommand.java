package com.winworldpc.winbot.Commands.commands.Admin;

import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Commands.Global.Error;
import com.winworldpc.winbot.Commands.Global.Errors_Permissions;
import com.winworldpc.winbot.Config;
import com.winworldpc.winbot.Interfaces.ICommand;
import com.winworldpc.winbot.Variables.Constants;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;


public class SetPrefixCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        Errors_Permissions perms = new Errors_Permissions();
        Error error = new Error();
        //TODO: Implement an Error File.
        if(!member.hasPermission(Permission.MANAGE_SERVER)){
            perms.No_Permissions("Manage Server");
            return;
        }
        //TODO: Implement a usage file.
        if(args.isEmpty()){
            error.Missing_Argument("<User>");
            return;
        }

        String newPrefix = args.get(0);

        Constants.PREFIXES.put(ctx.getGuild().getIdLong(), newPrefix);

        channel.sendMessage("The new Prefix has been set to `" + newPrefix + "`").queue();
    }

    @Override
    public String getName() {
        return "setprefix";
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public String getHelp() {
        return "Sets the prefix for this server.\n" +
                "Usage: `" + Config.PREFIX + getName() + " <prefix>`";
    }
}
