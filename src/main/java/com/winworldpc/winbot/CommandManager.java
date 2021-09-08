package com.winworldpc.winbot;

import com.winworldpc.winbot.Commands.Category.Miscellaneous;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Commands.Category.Admin;

/*
import com.winworldpc.winbot.Commands.Category.Moderation;
import com.winworldpc.winbot.Commands.Category.Music;
*/
import com.winworldpc.winbot.Commands.Global.Embeds;
import com.winworldpc.winbot.Interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();
    List<List<ICommand>> category = new ArrayList<>();
    public CommandManager(){

        Admin admin = new Admin();
        Miscellaneous misc = new Miscellaneous(this);
        /*Moderation mod = new Moderation();
        Music music = new Music();*/

        category.add(admin.importCommands());
        category.add(misc.importCommands());
        /*category.add(mod.importCommands());
        category.add(music.importCommands());*/


        for(List<ICommand>cat : category){
            for(ICommand cmd : cat){
                boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
                if(!nameFound){
                    commands.add(cmd);
                }

            }
        }

    }

    public List<ICommand> getCommands(){
        return commands;
    }
    @Nullable
   public ICommand getCommand(String search){
        String searchLower = search.toLowerCase();

        for(ICommand cmd : this.commands){
            if(cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)){
                return cmd;
            }
        }
        return null;
    }



    void handle(GuildMessageReceivedEvent event){

        String[] split = event.getMessage().getContentRaw()
                .replaceFirst(("(?i)") + Pattern.quote(Config.PREFIX), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if(cmd != null){
            event.getChannel().sendTyping().queue();
            List<String>args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);
            new Embeds(ctx);
            cmd.handle(ctx);
        }
    }
}

