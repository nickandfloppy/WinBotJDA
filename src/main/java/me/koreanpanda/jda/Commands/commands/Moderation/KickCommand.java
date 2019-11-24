package me.koreanpanda.jda.Commands.commands.Moderation;

import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Error;
import me.koreanpanda.jda.Commands.Global.Errors_Permissions;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class KickCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        Member member = ctx.getMember();
        Member selfMember = ctx.getGuild().getSelfMember();
        List<Member> mentionedMembers = ctx.getMessage().getMentionedMembers();
        Error error = new Error();
        Errors_Permissions perms = new Errors_Permissions();
        if(args.isEmpty() || mentionedMembers.isEmpty()){
           channel.sendMessage(error.Missing_Argument());
            return;
        }

        Member target = mentionedMembers.get(0);
        String reason = String.join("", args.subList(1, args.size()));

        if(!member.hasPermission(Permission.KICK_MEMBERS) || !member.canInteract(target)){
            channel.sendMessage(perms.No_Permissions("Kick Members")).queue();
            return;
        }

        if(!selfMember.hasPermission(Permission.KICK_MEMBERS) || !selfMember.canInteract(target)){
            channel.sendMessage(perms.Self_No_Permissions("Kick Members")).queue();
            return;
        }

        ctx.getGuild().kick(target, String.format("Kick by: %#s, with reason: %s", ctx.getAuthor(), reason));

        channel.sendMessage("Success!").queue();


    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getCategory() {
        return "Moderation";
    }

    @Override
    public String getHelp() {
        return "Kicks a user off the server.\n" +
                "Usage: `"  + Config.get("prefix") + getName() + " <user> <reason>`";
    }
}
