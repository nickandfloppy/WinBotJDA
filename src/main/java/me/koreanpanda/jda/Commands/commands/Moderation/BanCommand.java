package me.koreanpanda.jda.Commands.commands.Moderation;
import me.koreanpanda.jda.Commands.Global.Error;
import me.koreanpanda.jda.Commands.Global.Errors_Permissions;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class BanCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        Member member = ctx.getMember();
        Member selfMember = ctx.getGuild().getSelfMember();
        Errors_Permissions perms = new Errors_Permissions();
        Error error = new Error();
        List<Member> mentionedMembers = ctx.getMessage().getMentionedMembers();
        //TODO: Implement an error file.
        if(mentionedMembers.isEmpty() || args.size() < 2){
            channel.sendMessage(error.Missing_Argument()).queue();
            return;
        }

        Member target = mentionedMembers.get(0);
        String reason = String.join("", args.subList(1, args.size()));
        //TODO:Implement a no permission file.
        if(!member.hasPermission(Permission.BAN_MEMBERS) || !member.canInteract(target)){

            channel.sendMessage(perms.No_Permissions("Ban Member")).queue();
            return;
        }
        if(!selfMember.hasPermission(Permission.BAN_MEMBERS) || !selfMember.canInteract(target)){
            channel.sendMessage(perms.Self_No_Permissions("Ban Members")).queue();
            return;
        }

        target.ban(1, reason);

        channel.sendMessage("Successful banned!").queue();

    }

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getCategory() {
        return "Moderation";
    }

    @Override
    public String getHelp() {
        return "Bans a user from the server.\n" +
                "Usage: `"  + Config.get("prefix") + getName() + " <user> <reason>`";
    }
}
