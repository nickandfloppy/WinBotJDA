package me.koreanpanda.jda.Commands.commands.Miscellaneous;

import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.koreanpanda.jda.Commands.CommandContext;
import me.koreanpanda.jda.Commands.Global.Error;
import me.koreanpanda.jda.Config;
import me.koreanpanda.jda.Interfaces.ICommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class WhoisCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        Error error = new Error();
        //TODO: Implement a Error message
        if(args.isEmpty()){
            channel.sendMessage(error.Missing_Argument("User"));
            return;
        }
        String joined = String.join("", args);
        List<User> foundUsers = FinderUtil.findUsers(joined, ctx.getJDA());
        //TODO: Implement a list of errors to send in a different file.
        if(foundUsers.isEmpty()){
           List<Member> foundMembers = FinderUtil.findMembers(joined, ctx.getGuild());

           if(foundMembers.isEmpty()){
               return;
           }
           foundUsers = foundMembers.stream().map(Member::getUser).collect(Collectors.toList());
        }

        User user = foundUsers.get(0);
        Member member = ctx.getGuild().getMember(user);

        MessageEmbed builder = EmbedUtils.defaultEmbed()
                .setColor(member.getColor())
                .setThumbnail(user.getEffectiveAvatarUrl().replaceFirst("gif", "png"))
                .addField("Username#Discriminator", String.format("%#s", user), false)
                .addField("Display Name", member.getEffectiveName(), false)
                .addField("User Id + Mention", String.format("%s(%s)", user.getId(), member.getAsMention()), false)
                .addField("Account Created", user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Guild Joined", member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Online Status", member.getOnlineStatus().name().toLowerCase().replaceAll("_", " "), false)
                .addField("Bot Account", user.isBot() ? "Yes" : "No", false)
                .build();

        channel.sendMessage(builder).queue();
    }

    @Override
    public String getName() {
        return "whois";
    }

    @Override
    public String getCategory() {
        return "Information";
    }

    @Override
    public String getHelp() {
        return "Displays information about a user.\n" +
                "Usage: `" + Config.get("prefix") + getName() + "[user name/@user/user id]";
    }
}
