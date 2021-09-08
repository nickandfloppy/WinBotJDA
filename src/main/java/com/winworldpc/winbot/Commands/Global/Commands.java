package com.winworldpc.winbot.Commands.Global;

import com.winworldpc.winbot.Interfaces.ICommand;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Commands {
    public Commands(){

    }
    private final List<ICommand> commands = new ArrayList<>();
    public void addCommand(ICommand cmd){
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if(nameFound){
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
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
}
