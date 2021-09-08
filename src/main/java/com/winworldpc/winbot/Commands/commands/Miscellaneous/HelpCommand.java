package com.winworldpc.winbot.Commands.commands.Miscellaneous;

import me.duncte123.botcommons.messaging.EmbedUtils;
import com.winworldpc.winbot.CommandManager;
import com.winworldpc.winbot.Commands.CommandContext;
import com.winworldpc.winbot.Config;
import com.winworldpc.winbot.Interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.List;

public class HelpCommand implements ICommand {
    private final CommandManager manager;
    public HelpCommand(CommandManager manager){
        this.manager = manager;
    }
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if(args.isEmpty()){
            generateAndSendHelpEmbed(channel);
        }
        else {
            String search = args.get(0);
            ICommand command = manager.getCommand(search);

            if(command == null){
                channel.sendMessage("Nothing found for " + search).queue();
                return;
            }
            generateAndSendCommandEmbed(channel, search);
        }

    }



    private void generateAndSendCommandEmbed(TextChannel channel, String search){
        EmbedBuilder builder = EmbedUtils.defaultEmbed().setTitle("Info on " + search).setColor(Config.EMBED_COLOR);
        ICommand cmd = manager.getCommand(search);
        builder.setDescription(cmd.getHelp());
        builder.addField("Category", cmd.getCategory(), true);
        if(!cmd.getAliases().isEmpty()){
            StringBuilder str = new StringBuilder();
            for(String alias : cmd.getAliases()) {
                str.append("-").append(alias).append("\n");
            }
            builder.addField("Aliases", str.toString(), true);
        }
        channel.sendMessage(builder.build()).queue();
    }

    private void generateAndSendHelpEmbed(TextChannel channel){
        EmbedBuilder builder = EmbedUtils.defaultEmbed().setTitle("A list of all my commands:").setColor(Config.EMBED_COLOR);
        StringBuilder miscBuilder = new StringBuilder();
        StringBuilder infoBuilder = new StringBuilder();
        StringBuilder adminBuilder = new StringBuilder();
        //StringBuilder modBuilder = new StringBuilder();
        //StringBuilder musicBuilder = new StringBuilder();
        for(ICommand cmd : manager.getCommands()){
            if(cmd.getCategory()== "Miscellaneous"){
                miscBuilder.append(Config.PREFIX).append(cmd.getName()).append("\n");
            }
            else if(cmd.getCategory() == "Information"){
                infoBuilder.append(Config.PREFIX).append(cmd.getName()).append("\n");
            }
            else if(cmd.getCategory() == "Admin"){
                adminBuilder.append(Config.PREFIX).append(cmd.getName()).append("\n");
            }
            /*else if(cmd.getCategory() == "Moderation"){
                modBuilder.append(Config.get("prefix")).append(cmd.getName()).append("\n");
            }*/
            /*else if(cmd.getCategory() == "Music"){
                musicBuilder.append(Config.get("prefix")).append(cmd.getName()).append("\n");
            }*/

        }
        builder.addField("Admin", adminBuilder.toString(), true);
        builder.addField("Miscellaneous", miscBuilder.toString(), true);
        /*builder.addField("Moderation", modBuilder.toString(), true);
        builder.addField("Music", musicBuilder.toString(), true);*/
        builder.addField("Information", infoBuilder.toString(), true);


        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Shows the list with commands in the bot\n" +
                "Usage: `" + Config.PREFIX + "help [command]`";
    }
    @Override
    public String getCategory() {
        return "Miscellaneous";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("commands", "cmds", "commandlist");
    }
}
