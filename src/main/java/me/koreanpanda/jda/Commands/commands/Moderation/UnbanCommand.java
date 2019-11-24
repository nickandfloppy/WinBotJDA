package me.koreanpanda.jda.Commands.commands.Moderation;

import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Error;
import me.koreanpanda.jda.Commands.Global.Errors_Permissions;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UnbanCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        Error error = new Error();
        Errors_Permissions perms = new Errors_Permissions();
        if(!ctx.getMember().hasPermission(Permission.BAN_MEMBERS)){
            channel.sendMessage(perms.No_Permissions("Ban Members")).queue();
            return;
        }

        if(!ctx.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            channel.sendMessage(perms.Self_No_Permissions("Ban Members")).queue();
            return;
        }

        if(args.isEmpty()){
            channel.sendMessage(error.Missing_Argument("User/user id/ username#disc")).queue();
            return;
        }

        String argsJoined = String.join(" ", args);

        ctx.getGuild().retrieveBanList().queue((bans) ->{
            List<User> goodUsers = bans.stream().filter((ban) -> isCorrectUser(ban, argsJoined))
                    .map(Guild.Ban::getUser).collect(Collectors.toList());

            if(goodUsers.isEmpty()){
                channel.sendMessage("This user is not banned").queue();
                return;
            }

            User target = goodUsers.get(0);
            String mod = String.format("%#s", ctx.getAuthor());
            String bannedUser = String.format("%#s", target);
            ctx.getGuild().unban(target)
                    .reason("Unbanned By " + mod).queue();

            channel.sendMessage("User " + bannedUser + " unbanned.").queue();
        });

    }

    private boolean isCorrectUser(Guild.Ban ban, String arg){
        User bannedUser = ban.getUser();

        return bannedUser.getName().equalsIgnoreCase(arg) || bannedUser.getId().equals(arg) || String.format("%#s", bannedUser).equalsIgnoreCase(arg);
    }

    @Override
    public String getName() {
        return "unban";
    }

    @Override
    public String getCategory() {
        return "Moderation";
    }

    @Override
    public String getHelp() {
        return "Unbans a member from the server\n" +
                "Usage: `" + Config.get("prefix") + getName() + " <username/user id/username#disc>";
    }
}
